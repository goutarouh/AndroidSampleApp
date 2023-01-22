package com.github.goutarouh.simplerssreader.core.database.model.rss

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RssEntity(
    @PrimaryKey
    val rssLink: String = "",
    val title: String = "",
    val imageLink: String = ""
)
