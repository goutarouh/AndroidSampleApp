package com.github.goutarouh.androidsampleapp.core.repository.model.rss

import com.github.goutarouh.androidsampleapp.core.network.data.rss.RssApiModel
import com.github.goutarouh.androidsampleapp.core.network.data.rss.RssItemApiModel

data class Rss (
    val title: String,
    val items: List<RssItem>
)

data class RssItem(
    val title: String,
    val link: String
)

fun RssApiModel.toRss(): Rss = Rss(
    title = title,
    items = items.map { it.toRssItem() }
)

fun RssItemApiModel.toRssItem(): RssItem = RssItem(
    title = title,
    link = link
)