package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.goutarouh.simplerssreader.core.repository.RssRepository
import com.github.goutarouh.simplerssreader.core.util.data.Result
import com.github.goutarouh.simplerssreader.core.util.string.decode64
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
        when (val result = rssRepository.getRss(rssLink)) {
            is Result.Success -> {
                _uiState.emit(RssItemListScreenUiState.Success(result.data))
            }
            is Result.Error -> {
                _uiState.emit(RssItemListScreenUiState.Error(result.e))
            }
        }
    }

    fun setAutoFetch(rssLink: String, isAutoFetch: Boolean) {
        viewModelScope.launch {
            try {
                rssRepository.setAutoFetch(rssLink, isAutoFetch)
            } catch (e: Exception) {
                // TODO uiStateに送出するほどのエラーではない
            }

            updateUiState(rssLink)

            val state = uiState.value
            if (state is RssItemListScreenUiState.Success) {
                if (isAutoFetch) {
                    rssRepository.registerWorker(state.rss.rssLink, state.rss.title)
                } else {
                    rssRepository.unRegisterWorker(state.rss.rssLink)
                }
            }
        }

    }

    fun updateRss(rssLink: String) {
        viewModelScope.launch {
            when (val result = rssRepository.updateRss(rssLink, false)) {
                is Result.Success -> {
                    _uiState.emit(RssItemListScreenUiState.Success(result.data))
                }
                is Result.Error -> {
                    _uiState.emit(RssItemListScreenUiState.Error(result.e))
                }
            }
        }
    }
}

private const val rssLinkArg = "rssLink"
internal class RssItemListNavArgs(val rssLink: String) {
    constructor(savedStateHandle: SavedStateHandle): this(checkNotNull(savedStateHandle[rssLinkArg]) as String)
}