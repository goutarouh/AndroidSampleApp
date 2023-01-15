package com.github.goutarouh.androidsampleapp.feature.rss.rsshome.model

data class RssLinkInputText(
    val input: String = "",
    val isError: Boolean = false,
) {

    fun getStatus(): RssLinkInputStatus {
        if (input.isEmpty()) {
            return RssLinkInputStatus.ShouldNotEmpty
        }
        if (!input.startWithHttps()) {
            return RssLinkInputStatus.ShouldStartWithHttps
        }
        return RssLinkInputStatus.Valid
    }

    private fun String.startWithHttps(): Boolean {
        return startsWith("https://")
    }
}