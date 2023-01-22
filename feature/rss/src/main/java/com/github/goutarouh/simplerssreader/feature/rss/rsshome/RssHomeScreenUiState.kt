package com.github.goutarouh.simplerssreader.feature.rss.rsshome

import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss

sealed interface RssHomeScreenUiState {
    object Initial: RssHomeScreenUiState
    data class Error(val e: Exception): RssHomeScreenUiState
    data class Success(
        val rssList: List<Rss>
    ): RssHomeScreenUiState
}