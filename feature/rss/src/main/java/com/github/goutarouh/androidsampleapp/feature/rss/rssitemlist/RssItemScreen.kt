package com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.core.ui.theme.BlueGray100
import com.github.goutarouh.androidsampleapp.core.ui.theme.Red300
import com.github.goutarouh.androidsampleapp.core.util.localdate.formatForUi
import com.github.goutarouh.androidsampleapp.feature.rss.R
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
                        setAutoFetch = { rssLink, isAutoFetch ->
                            viewModel.setAutoFetch(rssLink, isAutoFetch)
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
    setAutoFetch: (String, Boolean) -> Unit,
    onCardClick: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            RssItemListHeader(
                rss = rss,
                update = update,
                setAutoFetch = setAutoFetch,
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
    setAutoFetch: (String, Boolean) -> Unit
) {

    var isAutoFetch by remember { mutableStateOf(rss.isAutoFetch) }

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
            val newAutoRenew = !isAutoFetch
            isAutoFetch = newAutoRenew
            setAutoFetch(rss.rssLink, newAutoRenew)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.auto_renew),
                contentDescription = null,
                tint = if (isAutoFetch) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
            )
        }
        IconButton(onClick = { update(rss.rssLink) }) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}