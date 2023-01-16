package com.github.goutarouh.androidsampleapp.core.database.model.rss

import androidx.room.Embedded
import androidx.room.Relation

data class RssWrapperData(
    @Embedded
    val rssEntity: RssEntity,

    @Relation(
        parentColumn = "rssLink",
        entityColumn = "rssLink"
    )
    val rssMetaEntity: RssMetaEntity,

    @Relation(
        parentColumn = "rssLink",
        entityColumn = "rssLink"
    )
    val items: List<RssItemEntity>,
)