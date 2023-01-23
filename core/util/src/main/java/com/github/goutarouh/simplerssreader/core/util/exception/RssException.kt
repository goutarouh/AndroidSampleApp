package com.github.goutarouh.simplerssreader.core.util.exception

abstract class RssException(
    open val rssLink: String
): Exception()