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
    val rssFavoriteEntity: RssFavoriteEntity,

    @Relation(
        parentColumn = "rssLink",
        entityColumn = "rssLink"
    )
    val items: List<RssItemEntity>,

    @Relation(
        parentColumn = "rssLink",
        entityColumn = "rssLink"
    )
    val rssUpdateEntity: RssUpdateEntity
)