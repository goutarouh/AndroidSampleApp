package com.github.goutarouh.androidsampleapp.feature.featurea.rssitemlist

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@Composable
fun RssItemListTopBar(
    state: RssItemListScreenUiState,
    rssItemScreenAction: RssItemScreenAction
) {
    when (state) {
        is RssItemListScreenUiState.Loading, is RssItemListScreenUiState.Error -> {
            LoadingOrError(rssItemScreenAction)
        }
        is RssItemListScreenUiState.Success -> {
            Success(state, rssItemScreenAction)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoadingOrError(
    rssItemScreenAction: RssItemScreenAction,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = "RSS LIST")
        },
        navigationIcon = {
            IconButton(onClick = { rssItemScreenAction.navigateBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Success(
    state: RssItemListScreenUiState.Success,
    rssItemScreenAction: RssItemScreenAction,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = state.rss.title)
        },
        navigationIcon = {
            IconButton(onClick = { rssItemScreenAction.navigateBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}
