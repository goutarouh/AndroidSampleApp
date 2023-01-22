package com.github.goutarouh.simplerssreader.core.repository.model.rss

class NoRssItemException(
    val rssLink: String
): Exception()