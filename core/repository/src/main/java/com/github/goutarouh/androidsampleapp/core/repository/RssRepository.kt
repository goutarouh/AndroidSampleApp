package com.github.goutarouh.androidsampleapp.core.repository

import com.github.goutarouh.androidsampleapp.core.database.dao.RssDao
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssFavoriteEntity
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
    suspend fun changeFavorite(rssLink: String, isFavorite: Boolean)
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
        val rssEntity = rssApiModel.toRssEntity(rssLink)
        val rssItemEntityList = rssApiModel.items.map {
            it.toRssItemEntity(rssLink)
        }
        val rssFavoriteEntity = RssFavoriteEntity(rssLink)

        rssDao.insertRssEntity(rssEntity)
        rssDao.insertRssItemEntityList(rssItemEntityList)
        rssDao.insertRssFavoriteEntity(rssFavoriteEntity)
        return@withContext rssEntity.toRss(rssItemEntityList)
    }

    override suspend fun changeFavorite(rssLink: String, isFavorite: Boolean) {
        rssDao.updateRssFavoriteEntity(RssFavoriteEntity(rssLink, isFavorite))
    }


}

