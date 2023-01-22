package com.github.goutarouh.simplerssreader.feature.rss.rsshome.model

sealed interface RssLinkInputStatus {
    object Valid: RssLinkInputStatus
    object ShouldStartWithHttps: RssLinkInputStatus
    object ShouldNotEmpty: RssLinkInputStatus
}