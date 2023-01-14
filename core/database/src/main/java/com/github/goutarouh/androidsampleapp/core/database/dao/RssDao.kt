package com.github.goutarouh.androidsampleapp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssFavoriteEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RssDao {

    @Query("SELECT * FROM RssEntity")
    fun getRssEntityListFlow(): Flow<List<RssEntity>>

    @Query("SELECT * FROM RssEntity JOIN RssFavoriteEntity ON RssEntity.rssLink = RssFavoriteEntity.rssLink WHERE RssFavoriteEntity.isFavorite = 1")
    fun getFavoriteRssEntityListFlow(): Flow<List<RssEntity>>

    @Query("SELECT * FROM RssEntity JOIN RssFavoriteEntity ON RssEntity.rssLink = RssFavoriteEntity.rssLink WHERE RssFavoriteEntity.isFavorite = 0")
    fun getNonFavoriteRssEntityListFlow(): Flow<List<RssEntity>>

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRssFavoriteEntity(rssFavoriteEntity: RssFavoriteEntity)

    @Update
    fun updateRssFavoriteEntity(rssFavoriteEntity: RssFavoriteEntity)
}