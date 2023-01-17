package com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.core.ui.theme.BlueGray100
import com.github.goutarouh.androidsampleapp.core.ui.theme.Red300
import com.github.goutarouh.androidsampleapp.core.util.localdate.formatForUi
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
            RssItemListTopBar(uiState.value, rssItemScreenAction)
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
                        update = {
                            viewModel.updateRss(it)
                        },
                        favorite = { rssLink, isFavorite ->
                            viewModel.changeFavorite(rssLink, isFavorite)
                            viewModel.registerFeed(isFavorite)
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
    update: (String) -> Unit,
    favorite: (String, Boolean) -> Unit,
    onCardClick: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            RssItemListHeader(
                rss = rss,
                update = update,
                favorite = favorite,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
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

@Composable
fun RssItemListHeader(
    rss: Rss,
    modifier: Modifier = Modifier,
    update: (String) -> Unit,
    favorite: (String, Boolean) -> Unit
) {

    var isFavorite by remember { mutableStateOf(rss.isFavorite) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f).padding(start = 16.dp),
        ) {
            Text(
                text = "Last update: ",
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = rss.lastFetchedAt.formatForUi(),
                style = MaterialTheme.typography.caption,
            )
        }
        IconButton(onClick = {
            val newFavorite = !isFavorite
            isFavorite = newFavorite
            favorite(rss.rssLink, newFavorite)
        }) {
            if (isFavorite) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = null,
                    tint = Red300
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = null,
                    tint = BlueGray100
                )
            }
        }
        IconButton(onClick = { update(rss.rssLink) }) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                tint = BlueGray100
            )
        }
    }
}