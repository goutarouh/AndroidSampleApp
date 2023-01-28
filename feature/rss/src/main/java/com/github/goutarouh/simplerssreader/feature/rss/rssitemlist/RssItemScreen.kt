package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.goutarouh.simplerssreader.core.repository.model.rss.NoRssItemException
import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import com.github.goutarouh.simplerssreader.core.util.exception.ParseException
import com.github.goutarouh.simplerssreader.core.util.exception.RssException
import com.github.goutarouh.simplerssreader.core.util.localdate.formatForUi
import com.github.goutarouh.simplerssreader.feature.rss.R
import com.github.goutarouh.simplerssreader.feature.rss.rssitemlist.RssItemListScreenUiState.*

interface RssItemScreenAction {
    fun navigateBack()
    fun itemClick(linkString: String)
}

interface RssItemSettingAction {
    fun setAutoFetch(rssLink: String, isAutoFetch: Boolean)
    fun setNotificationEnabled(rssLink: String, enabled: Boolean)
}

@Composable
fun RssItemListScreen(
    rssItemScreenAction: RssItemScreenAction,
    modifier: Modifier = Modifier,
    viewModel: RssItemListScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            RssItemListTopBar(
                state = uiState.value,
                rssItemScreenAction = rssItemScreenAction,
                rssItemSettingAction = object: RssItemSettingAction {
                    override fun setAutoFetch(rssLink: String, isAutoFetch: Boolean) {
                        viewModel.setAutoFetch(rssLink, isAutoFetch)
                    }

                    override fun setNotificationEnabled(rssLink: String, enabled: Boolean) {
                        viewModel.setPushNotification(rssLink, enabled)
                    }

                }
            )
        }
    ) {
        Box(modifier = modifier
            .padding(it)
            .fillMaxSize()
        ) {
            when (val state = uiState.value) {
                is Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is Error -> {
                    ErrorScreen(e = state.e)
                }
                is Success -> {
                    val context = LocalContext.current
                    LaunchedEffect(state) {
                        val workerEvent = state.workerEvent
                        if (workerEvent != null) {
                            scaffoldState.snackbarHostState.showSnackbar(message = context.getString(workerEvent.stringId), duration = SnackbarDuration.Short)
                            viewModel.setWorkerEventDone()
                        }
                    }
                    RssItemList(
                        rss = state.rss,
                        update = {
                            viewModel.updateRss(it)
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
private fun ErrorScreen(
    e: RssException,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.error),
                contentDescription = null,
                modifier = Modifier.size(70.dp),
                tint = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            when (e) {
                is ParseException -> {
                    Text(
                        text = stringResource(id = R.string.rss_get_error_not_supported_format),
                        modifier = Modifier.padding(horizontal = 24.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
                is NoRssItemException -> {
                    Text(
                        text = stringResource(id = R.string.rss_get_error_no_item),
                        modifier = Modifier.padding(horizontal = 24.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
                else -> {
                    Text(
                        text = stringResource(id = R.string.rss_get_error),
                        modifier = Modifier.padding(horizontal = 24.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            SelectionContainer {
                Text(
                    text = e.rssLink,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RssItemList(
    rss: Rss,
    update: (String) -> Unit,
    onCardClick: (String) -> Unit
) {
    var refreshing by remember { mutableStateOf(false) }
    val state = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            refreshing = true
            update(rss.rssLink)
        }
    )

    LaunchedEffect(rss.lastFetchedAt) {
        refreshing = false
    }

    Box(Modifier.pullRefresh(state)) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                RssItemListHeader(
                    rss = rss,
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
        PullRefreshIndicator(
            refreshing = refreshing,
            state = state,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun RssItemListHeader(
    rss: Rss,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
        modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.rss_items_last_update),
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = rss.lastFetchedAt.formatForUi(),
                style = MaterialTheme.typography.caption,
            )
        }
    }
}