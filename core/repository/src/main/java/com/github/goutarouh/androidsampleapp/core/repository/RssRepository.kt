package com.github.goutarouh.androidsampleapp.core.repository

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.github.goutarouh.androidsampleapp.core.database.TransactionProcessExecutor
import com.github.goutarouh.androidsampleapp.core.database.dao.RssDao
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssMetaEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssWrapperData
import com.github.goutarouh.androidsampleapp.core.network.data.rss.RssApiModel
import com.github.goutarouh.androidsampleapp.core.network.service.ZennRssService
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.*
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.toRss
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.toRssEntity
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.toRssItemEntity
import com.github.goutarouh.androidsampleapp.core.repository.workmanager.RssFetchWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

interface RssRepository {
    fun getRssListFlow(): Flow<List<Rss>>
    suspend fun updateRss(rssLink: String, isInit: Boolean): Rss
    suspend fun getRss(rssLink: String): Rss
    suspend fun changeFavorite(rssLink: String, isFavorite: Boolean)
    suspend fun checkUpdatedItemCount(rssLink: String): Int
    suspend fun deleteRss(rssLink: String)
    fun registerWorker(rssLink: String, title: String): Boolean
    fun unRegisterWorker(rssLink: String): Boolean
}

internal class RssRepositoryImpl(
    val context: Context,
    val transactionProcessExecutor: TransactionProcessExecutor,
    val zennRssService: ZennRssService,
    val rssDao: RssDao
): RssRepository {

    override fun getRssListFlow(): Flow<List<Rss>> {
        val rssList = rssDao.getRssWrapperDataListFlow()
        return rssList.map {
            it.map { it.toRss() }
        }
    }

    override suspend fun updateRss(rssLink: String, isInit: Boolean): Rss = withContext(Dispatchers.IO) {
        val rssApiModel = zennRssService.getRss(rssLink)

        if (rssApiModel.items.isEmpty()) {
            throw NoRssItemException(rssLink)
        }

        val rssWrapperData = writeRssToDb(rssLink, rssApiModel, isInit)
        return@withContext rssWrapperData.toRss()
    }

    override suspend fun getRss(rssLink: String): Rss = withContext(Dispatchers.IO) {

        val hasRss = rssDao.hasRssEntity(rssLink)
        return@withContext if (hasRss) {
            val rssWrapperData = rssDao.getRssWrapperData(rssLink)
            rssWrapperData.toRss()
        } else {
            updateRss(rssLink, true)
        }
    }

    override suspend fun changeFavorite(rssLink: String, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        rssDao.updateRssMetaEntity(rssLink, isFavorite)
    }

    override suspend fun deleteRss(rssLink: String) {
        unRegisterWorker(rssLink)
        withContext(Dispatchers.IO) {
            rssDao.deleteRssEntity(rssLink)
        }
    }

    override fun registerWorker(rssLink: String, title: String): Boolean {
        val request = PeriodicWorkRequestBuilder<RssFetchWorker>(
            24, TimeUnit.HOURS
        )
            .setInitialDelay(12, TimeUnit.HOURS)
            .setInputData(
                workDataOf(
                    RssFetchWorker.RSS_LINK to rssLink,
                    RssFetchWorker.RSS_TITLE to title
                )
            )
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            rssLink,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
        return true
    }

    override fun unRegisterWorker(rssLink: String): Boolean {
        return try {
            WorkManager.getInstance(context).cancelUniqueWork(rssLink)
            true
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun writeRssToDb(
        rssLink: String,
        rssApiModel: RssApiModel,
        isInit: Boolean
    ): RssWrapperData {
        val rssEntity = rssApiModel.toRssEntity(rssLink)
        val rssItemEntityList = rssApiModel.items.mapIndexed { index, rssItemApiModel ->
            rssItemApiModel.toRssItemEntity(index, rssLink)
        }

        transactionProcessExecutor.doTransactionProcess {
            val rssMetaEntity = if (isInit) {
                RssMetaEntity(rssLink = rssLink, isFavorite = false, lastFetchedAt = LocalDateTime.now())
            } else {
                rssDao.getRssMetaEntity(rssLink).copy(lastFetchedAt = LocalDateTime.now())
            }
            rssDao.deleteRssEntity(rssLink)
            rssDao.insertRssEntity(rssEntity)
            rssDao.insertRssItemEntityList(rssItemEntityList)
            rssDao.insertRssMetaEntity(rssMetaEntity)
        }
        return rssDao.getRssWrapperData(rssLink)
    }

    override suspend fun checkUpdatedItemCount(rssLink: String): Int {
        val rssApiModel = withContext(Dispatchers.Default) {
            zennRssService.getRss(rssLink)
        }

        val fetchedRssItemEntityList = rssApiModel.items.mapIndexed { index, rssItemApiModel ->
            rssItemApiModel.toRssItemEntity(index, rssLink)
        }
        val existedRssItemEntityList = withContext(Dispatchers.IO) {
            rssDao.getRssItemEntityList(rssLink)
        }

        if (existedRssItemEntityList.isEmpty()) {
            return 0
        }

        if (fetchedRssItemEntityList.size != existedRssItemEntityList.size) {
            return (fetchedRssItemEntityList.size - existedRssItemEntityList.size).coerceAtLeast(0)
        }
        val existedLatestItem = existedRssItemEntityList.first()

        if (!fetchedRssItemEntityList.contains(existedLatestItem)) {
            return fetchedRssItemEntityList.size
        }

        writeRssToDb(rssLink, rssApiModel, false)

        return fetchedRssItemEntityList.dropLastWhile { it == existedLatestItem }.size
    }

}

