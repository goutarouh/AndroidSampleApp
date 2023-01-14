package com.github.goutarouh.androidsampleapp.core.network.data.rss

data class RssApiModel(
    val title: String,
    val items: List<RssItemApiModel>
)