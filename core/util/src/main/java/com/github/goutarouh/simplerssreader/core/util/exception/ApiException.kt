package com.github.goutarouh.simplerssreader.core.util.exception

class ApiException(
    override val rssLink: String
): RssException(rssLink)