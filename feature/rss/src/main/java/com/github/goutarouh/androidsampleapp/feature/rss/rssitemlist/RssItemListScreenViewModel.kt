package com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.goutarouh.androidsampleapp.core.repository.RssRepository
import com.github.goutarouh.androidsampleapp.core.util.string.decode64
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RssItemListScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val rssRepository: RssRepository
): ViewModel() {

    private val rssItemListNavArgs = RssItemListNavArgs(savedStateHandle)
    val rssItemLink = rssItemListNavArgs.rssLink.decode64()

    private val _uiState = MutableStateFlow<RssItemListScreenUiState>(RssItemListScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateUiState(rssItemLink)
        }
    }

    private suspend fun updateUiState(rssLink: String) {
        try {
            val rss = rssRepository.getRss(rssLink)
            _uiState.emit(RssItemListScreenUiState.Success(rss))
        } catch (e: Exception) {
            _uiState.emit(RssItemListScreenUiState.Error(e))
        }
    }

    fun changeFavorite(rssLink: String, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                rssRepository.changeFavorite(rssLink, isFavorite)
            } catch (e: Exception) {
                // TODO uiStateに送出するほどのエラーではない
            }
        }

    }

}

private const val rssLinkArg = "rssLink"
internal class RssItemListNavArgs(val rssLink: String) {
    constructor(savedStateHandle: SavedStateHandle): this(checkNotNull(savedStateHandle[rssLinkArg]) as String)
}