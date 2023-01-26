package com.github.goutarouh.simplerssreader.core.database.model.rss

import androidx.room.Embedded
import androidx.room.Relation

data class RssWrapperData(
    @Embedded
    val rssEntity: RssEntity = RssEntity(),

    @Relation(
        parentColumn = "rssLink",
        entityColumn = "rssLink"
    )
    val rssMetaEntity: RssMetaEntity = RssMetaEntity(),

    @Relation(
        parentColumn = "rssLink",
        entityColumn = "rssLink"
    )
    val items: List<RssItemEntity> = listOf(),
)