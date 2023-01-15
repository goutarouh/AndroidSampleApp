package com.github.goutarouh.androidsampleapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.goutarouh.androidsampleapp.core.database.dao.RssDao
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssFavoriteEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssItemEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssUpdateEntity

@Database(
    entities = [
        RssEntity::class,
        RssItemEntity::class,
        RssFavoriteEntity::class,
        RssUpdateEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun rssDao(): RssDao
}