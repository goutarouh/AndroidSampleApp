package com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist

import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss

sealed interface RssItemListScreenUiState {

    object Loading: RssItemListScreenUiState
    data class Error(
        val e: Exception
    ): RssItemListScreenUiState
    data class Success(
        val rss: Rss
    ): RssItemListScreenUiState

}