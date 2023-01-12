package com.github.goutarouh.androidsampleapp.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssDB
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssItemDB

@Dao
interface RssDao {

    @Query("SELECT * FROM RssDB")
    fun getRssDbALL(): List<RssDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRssDb(rssDB: RssDB)

    @Query("DELETE FROM RssDB WHERE title = :title")
    fun deleteRssDb(title: String)

    @Query("SELECT * FROM RssItemDB")
    fun getRssItemDbAll(): List<RssItemDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRssItemDbs(rssItemDBList: List<RssItemDB>)
}