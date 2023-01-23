package com.github.goutarouh.simplerssreader.core.repository.model.rss

import com.github.goutarouh.simplerssreader.core.util.exception.RssException

class NoRssItemException(
    override val rssLink: String
): RssException(rssLink)