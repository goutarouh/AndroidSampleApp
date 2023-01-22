package com.github.goutarouh.simplerssreader.core.database.model.rss

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = arrayOf("rssLink", "pageLink"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = RssEntity::class,
            parentColumns = arrayOf("rssLink"),
            childColumns = arrayOf("rssLink"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class RssItemEntity(
    val order: Int,
    val rssLink: String = "",
    val title: String = "",
    val pageLink: String = ""
)
