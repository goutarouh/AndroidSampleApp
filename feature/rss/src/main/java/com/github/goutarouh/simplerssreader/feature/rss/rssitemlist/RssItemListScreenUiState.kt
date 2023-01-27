package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist

import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import com.github.goutarouh.simplerssreader.core.util.exception.RssException

sealed interface RssItemListScreenUiState {

    object Loading: RssItemListScreenUiState
    data class Error(
        val e: RssException
    ): RssItemListScreenUiState
    data class Success(
        val rss: Rss
    ): RssItemListScreenUiState

}


enum class SetWorkerEvent(
    val stringId: Int
) {
    RegisteredSuccess(R.string.rss_register_success),
    RegisteredFailed(R.string.rss_register_failed),
    UnRegisteredSuccess(R.string.rss_unregister_success),
    UnRegisteredFailed(R.string.rss_unregister_failed),
}