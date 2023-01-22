package com.github.goutarouh.simplerssreader.core.database.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun fromString(value: String): LocalDateTime {
        return LocalDateTime.parse(value)
    }
    @TypeConverter
    fun fromLocalDateTime(date: LocalDateTime): String {
        return date.toString()
    }
}