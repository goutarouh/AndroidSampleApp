package com.github.goutarouh.androidsampleapp.core.database.dao

import androidx.room.*
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssFavoriteEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssItemEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssWrapperData
import kotlinx.coroutines.flow.Flow

@Dao
interface RssDao {

    @Transaction
    @Query("SELECT * FROM RssEntity")
    suspend fun getRssWrapperDataList(): List<RssWrapperData>

    @Transaction
    @Query("SELECT * FROM RssEntity")
    fun getRssWrapperDataListFlow(): Flow<List<RssWrapperData>>

    @Transaction
    @Query("SELECT * FROM RssEntity WHERE rssLink = :rssLink")
    suspend fun getRssWrapperData(rssLink: String): RssWrapperData

    @Transaction
    @Query("SELECT EXISTS(SELECT 1 FROM RssEntity WHERE rssLink = :rssLink LIMIT 1)")
    suspend fun hasRssEntity(rssLink: String): Boolean

    @Query("SELECT * FROM RssEntity")
    fun getRssEntityList(): List<RssEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRssEntity(rssEntity: RssEntity)

    @Query("DELETE FROM RssEntity WHERE rssLink = :rssLink")
    fun deleteRssEntity(rssLink: String)

    @Query("SELECT * FROM RssItemEntity")
    fun getRssItemEntityList(): List<RssItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRssItemEntityList(rssItemEntityList: List<RssItemEntity>)

    @Query("SELECT * FROM RssFavoriteEntity WHERE rssLink = :rssLink")
    fun getRssFavorite(rssLink: String): RssFavoriteEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM RssFavoriteEntity WHERE rssLink = :rssLink LIMIT 1)")
    fun hasRssFavorite(rssLink: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRssFavoriteEntity(rssFavoriteEntity: RssFavoriteEntity)

    @Update
    fun updateRssFavoriteEntity(rssFavoriteEntity: RssFavoriteEntity)
}