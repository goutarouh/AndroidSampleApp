package com.github.goutarouh.androidsampleapp.feature.featurea.rssitemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.goutarouh.androidsampleapp.core.repository.RssRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RssItemListScreenViewModel @Inject constructor(
    private val rssRepository: RssRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<RssItemListScreenUiState>(RssItemListScreenUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateUiState()
        }
    }

    private suspend fun updateUiState() {
        try {
            val rss = rssRepository.getRss()
            _uiState.emit(RssItemListScreenUiState.Success(rss))
        } catch (e: Exception) {
            _uiState.emit(RssItemListScreenUiState.Error(e))
        }
    }

}