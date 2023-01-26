package com.github.goutarouh.simplerssreader.core.database.model.rss

import androidx.room.Entity
import androidx.room.ForeignKey
import java.time.LocalDateTime

@Entity(
    primaryKeys = arrayOf("rssLink"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = RssEntity::class,
            parentColumns = arrayOf("rssLink"),
            childColumns = arrayOf("rssLink"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class RssMetaEntity(
    val rssLink: String = "",
    val isAutoFetch: Boolean = false,
    val lastFetchedAt: LocalDateTime = LocalDateTime.now(),
    val unReadItemCount: Int = 0
)