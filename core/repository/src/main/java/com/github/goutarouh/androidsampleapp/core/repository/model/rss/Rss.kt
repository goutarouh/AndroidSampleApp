package com.github.goutarouh.androidsampleapp.core.repository.model.rss

import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssEntity
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssItemEntity
import com.github.goutarouh.androidsampleapp.core.network.data.rss.RssApiModel
import com.github.goutarouh.androidsampleapp.core.network.data.rss.RssItemApiModel

data class Rss (
    val title: String,
    val rssLink: String,
    val items: List<RssItem>
)

data class RssItem(
    val title: String,
    val pageLink: String
)

// --------------------- ApiModel -> Entity --------------------
internal fun RssApiModel.toRssEntity(rssLink: String): RssEntity = RssEntity(
    rssLink = rssLink,
    title = title
)

internal fun RssItemApiModel.toRssItemEntity(rssLink: String): RssItemEntity = RssItemEntity(
    rssLink = rssLink,
    title = title,
    pageLink = link
)

// --------------------- Entity -> UiModel --------------------
internal fun RssEntity.toRss(items: List<RssItemEntity>): Rss = Rss(
    title = title,
    rssLink = rssLink,
    items = items.map { it.toRssItem() }
)

internal fun RssItemEntity.toRssItem(): RssItem = RssItem(
    title = title,
    pageLink = pageLink
)