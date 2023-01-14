package com.github.goutarouh.androidsampleapp.feature.rss.rsshome.model

data class RssLinkInputText(
    val input: String
) {

    fun isValid(): Boolean {
        return input.isNotEmpty() && input.startWithHttps()
    }

    private fun String.startWithHttps(): Boolean {
        return startsWith("https://")
    }
}