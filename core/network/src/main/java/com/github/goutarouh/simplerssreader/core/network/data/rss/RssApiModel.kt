package com.github.goutarouh.simplerssreader.core.network.data.rss

data class RssApiModel(
    val title: String = "",
    val imageLink: String = "",
    val items: List<RssItemApiModel> = listOf()
)