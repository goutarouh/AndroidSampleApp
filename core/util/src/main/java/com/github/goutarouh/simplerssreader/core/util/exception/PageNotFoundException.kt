package com.github.goutarouh.simplerssreader.core.util.exception

class PageNotFoundException(
    override val rssLink: String
): RssException(rssLink)