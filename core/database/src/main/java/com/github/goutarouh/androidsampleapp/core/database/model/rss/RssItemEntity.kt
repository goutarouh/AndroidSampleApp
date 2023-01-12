package com.github.goutarouh.androidsampleapp.core.database.model.rss

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = arrayOf(
        ForeignKey(
            entity = RssDB::class,
            parentColumns = arrayOf("title"),
            childColumns = arrayOf("parentTitle"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class RssItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val parentTitle: String = "",
    val title: String,
    val link: String
)
