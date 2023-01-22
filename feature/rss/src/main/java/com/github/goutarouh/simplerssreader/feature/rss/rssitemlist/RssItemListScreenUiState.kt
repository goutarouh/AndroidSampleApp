package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist

import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss

sealed interface RssItemListScreenUiState {

    object Loading: RssItemListScreenUiState
    data class Error(
        val e: Exception
    ): RssItemListScreenUiState
    data class Success(
        val rss: Rss
    ): RssItemListScreenUiState

}