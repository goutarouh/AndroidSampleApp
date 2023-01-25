package com.github.goutarouh.simplerssreader.core.repository.model.rss

import com.github.goutarouh.simplerssreader.core.database.model.rss.RssEntity
import com.github.goutarouh.simplerssreader.core.database.model.rss.RssItemEntity
import com.github.goutarouh.simplerssreader.core.database.model.rss.RssWrapperData
import com.github.goutarouh.simplerssreader.core.network.data.rss.RssApiModel
import com.github.goutarouh.simplerssreader.core.network.data.rss.RssItemApiModel
import java.time.LocalDateTime

data class Rss (
    val title: String,
    val rssLink: String,
    val imageLink: String,
    val items: List<RssItem>,
    val isAutoFetch: Boolean,
    val lastFetchedAt: LocalDateTime,
    val unReadItemCount: Int
)

data class RssItem(
    val title: String,
    val pageLink: String
)

// --------------------- ApiModel -> Entity --------------------
internal fun RssApiModel.toRssEntity(rssLink: String): RssEntity = RssEntity(
    rssLink = rssLink,
    title = title,
    imageLink = imageLink
)

internal fun RssItemApiModel.toRssItemEntity(order: Int, rssLink: String): RssItemEntity = RssItemEntity(
    rssLink = rssLink,
    title = title,
    pageLink = link,
    order = order
)

// --------------------- DB -> UiModel --------------------
internal fun RssWrapperData.toRss(): Rss = Rss(
    title = rssEntity.title,
    rssLink = rssEntity.rssLink,
    imageLink = rssEntity.imageLink,
    items = items.map { it.toRssItem() },
    isAutoFetch = rssMetaEntity.isAutoFetch,
    lastFetchedAt = rssMetaEntity.lastFetchedAt,
    unReadItemCount = rssMetaEntity.unReadItemCount
)

internal fun RssItemEntity.toRssItem(): RssItem = RssItem(
    title = title,
    pageLink = pageLink
)