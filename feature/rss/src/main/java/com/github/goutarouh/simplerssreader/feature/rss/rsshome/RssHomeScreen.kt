package com.github.goutarouh.simplerssreader.feature.rss.rsshome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import com.github.goutarouh.simplerssreader.core.ui.dialog.DeleteConfirmDialog
import com.github.goutarouh.simplerssreader.core.util.navigation.UrlNavArg
import com.github.goutarouh.simplerssreader.feature.rss.R
import com.github.goutarouh.simplerssreader.feature.rss.rsshome.RssHomeScreenUiState.*
import com.github.goutarouh.simplerssreader.feature.rss.rsshome.composable.RssCard
import com.github.goutarouh.simplerssreader.feature.rss.rsshome.composable.RssLinkTextField
import com.github.goutarouh.simplerssreader.feature.rss.rssitemlist.RssItemListScreen
import com.github.goutarouh.simplerssreader.feature.rss.rssitemlist.RssItemScreenAction
import com.github.goutarouh.simplerssreader.feature.rss.webview.RssWebViewScreen

const val RSS_HOME_ROUTE = "RSS_HOME_ROUTE"
const val RSS_LIST_ROUTE = "RSS_LIST_ROUTE/{rssLink}"
const val RSS_WEB_ROUTE = "RSS_WEB_ROUTE/{rssLink}"

fun NavGraphBuilder.rssHome(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(route = RSS_HOME_ROUTE) {
        RssHomeScreen(
            navigateTo = {
                if (navController.currentDestination?.route == RSS_HOME_ROUTE) {
                    val url = with(UrlNavArg) { it.navArgEncode() }
                    navController.navigate(RSS_LIST_ROUTE.replace("{rssLink}", url))
                }
            },
            modifier = modifier
        )
    }
    composable(
        route = RSS_LIST_ROUTE,
        arguments = listOf(navArgument("rssLink") { type = NavType.StringType })
    ) {
        RssItemListScreen(
            rssItemScreenAction = object: RssItemScreenAction {
                override fun navigateBack() { navController.popBackStack() }
                override fun itemClick(linkString: String) {
                    if (navController.currentDestination?.route?.startsWith(RSS_LIST_ROUTE) == true) {
                        val url = with(UrlNavArg) { linkString.navArgEncode() }
                        navController.navigate(RSS_WEB_ROUTE.replace("{rssLink}", url))
                    }
                }
            },
            modifier = modifier
        )
    }
    composable(
        route = RSS_WEB_ROUTE,
        arguments = listOf(navArgument("rssLink") { type = NavType.StringType }),
    ) {
        val rssLink = with(UrlNavArg) { it.arguments?.getString("rssLink")!!.navArgDecode() }
        RssWebViewScreen(
            rssLink = rssLink,
            navigationBack = { navController.popBackStack() },
            modifier = modifier
        )
    }
}

@Composable
fun RssHomeScreen(
    navigateTo: (String) -> Unit,
    modifier: Modifier,
    viewModel: RssHomeScreenViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        when (val state = uiState.value) {
            is Initial -> {}
            is Error -> {
                Text(text = "${state.e}")
            }
            is Success -> {
                RssHome(
                    state = state,
                    openRssList = navigateTo,
                    deleteRss = { viewModel.deleteRss(it) },
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun RssHome(
    state: Success,
    openRssList: (String) -> Unit,
    deleteRss: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var showDeleteConfirmDialog by remember { mutableStateOf<Pair<String, String>?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                RssLinkTextField(
                    searchClick = openRssList,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }

            RssList(
                rssList = state.rssList,
                onCardClick = openRssList,
                onCardLongClick = { link, title ->
                    showDeleteConfirmDialog = link to title
                }
            )
        }


        if (showDeleteConfirmDialog != null) {
            DeleteConfirmDialog(
                deleteTargetName = showDeleteConfirmDialog!!.second,
                onConfirm = {
                    deleteRss(showDeleteConfirmDialog!!.first)
                    showDeleteConfirmDialog = null
                },
                onDismiss = { showDeleteConfirmDialog = null }
            )
        }

    }
}

fun LazyListScope.RssList(
    rssList: List<Rss>,
    onCardClick: (String) -> Unit,
    onCardLongClick: (String, String) -> Unit,
) {

    if (rssList.isEmpty()) {
        item {
            RssListEmpty(
                modifier = Modifier
            )
        }
    } else {
        items(rssList) { rss ->
            RssCard(
                rss = rss,
                onCardClick = onCardClick,
                onCardLongClick = onCardLongClick,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun RssListEmpty(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 18.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pan),
                modifier = Modifier.size(40.dp),
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.rss_empty),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
            Text(
                text = stringResource(id = R.string.rss_try_search),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        }
    }
}