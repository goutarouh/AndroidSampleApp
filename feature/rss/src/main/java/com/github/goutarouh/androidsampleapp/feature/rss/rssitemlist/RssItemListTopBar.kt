package com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss

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
            Success(state.rss, rssItemScreenAction)
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
    rss: Rss,
    rssItemScreenAction: RssItemScreenAction,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = rss.title)
        },
        navigationIcon = {
            IconButton(onClick = { rssItemScreenAction.navigateBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}
