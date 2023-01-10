package com.github.goutarouh.androidsampleapp.feature.featurea.rssitemlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.feature.featurea.rssitemlist.RssItemListScreenUiState.*


@Composable
fun RssItemListScreen(
    viewModel: RssItemListScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState.value) {
            is Loading -> {}
            is Success -> {
                RssItemList(rss = state.rss)
            }
        }
    }
}

@Composable
fun RssItemList(
    rss: Rss
) {
    Column {
        Text(text = rss.title)
        rss.items.forEach {
            Text(text = it.title)
        }
    }

}