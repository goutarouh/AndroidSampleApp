package com.github.goutarouh.simplerssreader.feature.rss.rsshome.model

data class RssLinkInputText(
    val input: String = URL_PROTOCOL,
    val isError: Boolean = false,
) {

    companion object {
        private const val URL_PROTOCOL = "https://"
    }

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
        return startsWith(URL_PROTOCOL)
    }
}