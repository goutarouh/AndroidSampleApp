package com.github.goutarouh.androidsampleapp.core.database.model.rss

import androidx.room.Entity
import androidx.room.ForeignKey

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
data class RssUpdateEntity(
    val rssLink: String,
    val updated: Boolean = false
)
