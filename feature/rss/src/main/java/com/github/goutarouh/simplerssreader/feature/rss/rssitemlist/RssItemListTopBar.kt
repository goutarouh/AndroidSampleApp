package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import com.github.goutarouh.simplerssreader.feature.rss.rssitemlist.settings.RssItemSettingDialog

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
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colors.background,
            titleContentColor = MaterialTheme.colors.onBackground
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Success(
    rss: Rss,
    rssItemScreenAction: RssItemScreenAction,
) {
    var showRssSettingDialog by remember { mutableStateOf(false) }
    if (showRssSettingDialog) {
        RssItemSettingDialog {
            showRssSettingDialog = false
        }
    }
    CenterAlignedTopAppBar(
        title = {
            Text(text = rss.title)
        },
        navigationIcon = {
            IconButton(onClick = { rssItemScreenAction.navigateBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {
                showRssSettingDialog = true
            }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colors.background,
            titleContentColor = MaterialTheme.colors.onBackground
        )
    )
}
