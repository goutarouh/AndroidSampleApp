package com.github.goutarouh.simplerssreader.core.util.exception

class ParseException(
    override val rssLink: String
): RssException(rssLink) {
}