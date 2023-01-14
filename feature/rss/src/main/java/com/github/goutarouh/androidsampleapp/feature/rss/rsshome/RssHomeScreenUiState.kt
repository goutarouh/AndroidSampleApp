package com.github.goutarouh.androidsampleapp.feature.rss.rsshome

import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss

sealed interface RssHomeScreenUiState {
    object Initial: RssHomeScreenUiState
    data class Error(val e: Exception): RssHomeScreenUiState
    data class Success(
        val rssFavoriteList: List<Rss>,
        val rssUnFavoriteList: List<Rss>
    ): RssHomeScreenUiState
}