package com.github.goutarouh.androidsampleapp.core.repository

import com.github.goutarouh.androidsampleapp.core.database.dao.RssDao
import com.github.goutarouh.androidsampleapp.core.network.service.ZennRssService
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.toRss
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.toRssEntity
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.toRssItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface RssRepository {
    suspend fun getRssListFlow(): Flow<List<Rss>>
    suspend fun getRss(rssLink: String): Rss
}

internal class RssRepositoryImpl(
    val zennRssService: ZennRssService,
    val rssDao: RssDao,
): RssRepository {

    override suspend fun getRssListFlow(): Flow<List<Rss>> {
        val rssEntityListFlow = rssDao.getRssEntityListFlow()
        return rssEntityListFlow.map {
            it.map { it.toRss(listOf()) }
        }
    }

    override suspend fun getRss(rssLink: String): Rss = withContext(Dispatchers.IO) {
        val rssApiModel = zennRssService.getRss(rssLink)
        val rssEntity = rssApiModel.toRssEntity()
        val rssItemEntityList = rssApiModel.items.map {
            it.toRssItemEntity(rssLink = rssEntity.rssLink)
        }
        rssDao.insertRssEntity(rssEntity)
        rssDao.insertRssItemEntityList(rssItemEntityList)
        return@withContext rssEntity.toRss(rssItemEntityList)
    }
}

