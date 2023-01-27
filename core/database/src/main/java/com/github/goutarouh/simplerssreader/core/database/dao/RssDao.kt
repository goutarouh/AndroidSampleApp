package com.github.goutarouh.simplerssreader.core.database.dao

import androidx.room.*
import com.github.goutarouh.simplerssreader.core.database.model.rss.*
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface RssDao {

    @Transaction
    @Query("SELECT * FROM RssEntity")
    suspend fun getRssWrapperDataList(): List<RssWrapperData>

    @Transaction
    @Query("SELECT * FROM RssEntity")
    fun getRssWrapperDataListFlow(): Flow<List<RssWrapperData>>

    @Transaction
    suspend fun getRssWrapperData(rssLink: String): RssWrapperData {
        return RssWrapperData(
            rssEntity = getRssEntity(rssLink),
            rssMetaEntity = getRssMetaEntity(rssLink),
            items = getRssItemEntityList(rssLink)
        )
    }

    @Query("SELECT * FROM RssEntity WHERE rssLink = :rssLink")
    suspend fun getRssEntity(rssLink: String): RssEntity

    @Transaction
    @Query("SELECT EXISTS(SELECT 1 FROM RssEntity WHERE rssLink = :rssLink LIMIT 1)")
    suspend fun hasRssEntity(rssLink: String): Boolean

    @Query("SELECT * FROM RssEntity")
    fun getRssEntityList(): List<RssEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRssEntity(rssEntity: RssEntity)

    @Query("DELETE FROM RssEntity WHERE rssLink = :rssLink")
    suspend fun deleteRssEntity(rssLink: String)

    @Query("SELECT * FROM RssItemEntity WHERE rssLink = :rssLink ORDER BY `order`")
    fun getRssItemEntityList(rssLink: String): List<RssItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRssItemEntityList(rssItemEntityList: List<RssItemEntity>)

    @Query("SELECT * FROM RssMetaEntity WHERE rssLink = :rssLink")
    fun getRssMetaEntity(rssLink: String): RssMetaEntity

    @Query("SELECT EXISTS(SELECT 1 FROM RssMetaEntity WHERE rssLink = :rssLink LIMIT 1)")
    fun hasRssMeta(rssLink: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRssMetaEntity(rssMetaEntity: RssMetaEntity)

    @Query("UPDATE RssMetaEntity SET isAutoFetch = :isAutoFetch WHERE rssLink = :rssLink")
    suspend fun updateRssMetaEntityWithAutoFetch(rssLink: String, isAutoFetch: Boolean)

    @Query("UPDATE RssMetaEntity SET lastFetchedAt = :lastFetchedAt WHERE rssLink = :rssLink")
    suspend fun updateRssMetaEntity(rssLink: String, lastFetchedAt: LocalDateTime)

    @Query("UPDATE RssMetaEntity SET unReadItemCount = :unReadItemCount WHERE rssLink = :rssLink")
    suspend fun updateRssMetaEntity(rssLink: String, unReadItemCount: Int)

    @Query("UPDATE RssMetaEntity SET isPushNotification = :isPushNotification WHERE rssLink = :rssLink")
    suspend fun updateRssMetaEntityWithPushNotification(rssLink: String, isPushNotification: Boolean)
}