package com.github.goutarouh.androidsampleapp.feature.featurea.rssitemlist

import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss

sealed interface RssItemListScreenUiState {

    object Loading: RssItemListScreenUiState
    data class Success(
        val rss: Rss
    ): RssItemListScreenUiState

}