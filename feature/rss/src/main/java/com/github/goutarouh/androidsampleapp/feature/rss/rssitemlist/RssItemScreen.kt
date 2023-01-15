package com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist.RssItemListScreenUiState.*

interface RssItemScreenAction {
    fun navigateBack()
    fun itemClick(linkString: String)
}

@Composable
fun RssItemListScreen(
    rssItemScreenAction: RssItemScreenAction,
    modifier: Modifier = Modifier,
    viewModel: RssItemListScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            RssItemListTopBar(uiState.value, rssItemScreenAction) { rssLink, isFavorite ->
                viewModel.registerFeed(isFavorite)
                viewModel.changeFavorite(rssLink, isFavorite)
            }
        }
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            when (val state = uiState.value) {
                is Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is Error -> {
                    Text(text = "${state.e}")
                }
                is Success -> {
                    RssItemList(
                        rss = state.rss,
                        subscribe = {
                            viewModel.registerFeed(true)
                        }
                    ) {
                        rssItemScreenAction.itemClick(it)
                    }
                }
            }
        }
    }
}


@Composable
fun RssItemList(
    rss: Rss,
    subscribe: (Boolean) -> Unit,
    onCardClick: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(rss.items) { rssItem ->
            RssItemCard(
                rssItem = rssItem,
                onCardClick = onCardClick,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}