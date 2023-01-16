package com.github.goutarouh.androidsampleapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.goutarouh.androidsampleapp.core.database.converter.LocalDateTimeConverter
import com.github.goutarouh.androidsampleapp.core.database.dao.RssDao
import com.github.goutarouh.androidsampleapp.core.database.model.rss.*

@Database(
    entities = [
        RssEntity::class,
        RssItemEntity::class,
        RssMetaEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    LocalDateTimeConverter::class
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun rssDao(): RssDao
}