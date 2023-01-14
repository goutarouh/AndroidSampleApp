package com.github.goutarouh.androidsampleapp.feature.rss.rsshome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.core.util.string.encode64
import com.github.goutarouh.androidsampleapp.feature.rss.rsshome.RssHomeScreenUiState.*
import com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist.RssItemListScreen
import com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist.RssItemScreenAction

const val RSS_HOME_ROUTE = "RSS_HOME_ROUTE"
const val RSS_LIST_ROUTE = "RSS_LIST_ROUTE/{rssLink}"

fun NavGraphBuilder.rssHome(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(route = RSS_HOME_ROUTE) {
        RssHomeScreen(
            navigateTo = { navController.navigate(RSS_LIST_ROUTE.replace("{rssLink}", it.encode64())) },
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
            },
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RssHomeScreen(
    navigateTo: (String) -> Unit,
    modifier: Modifier,
    viewModel: RssHomeScreenViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "RSS LIST")
                }
            )
        }
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            when (val state = uiState.value) {
                is Initial -> {}
                is Error -> {
                    Text(text = "${state.e}")
                }
                is Success -> {
                    RssHome(
                        rssList = state.rssList,
                        onCardClick = navigateTo,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RssHome(
    rssList: List<Rss>,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var input by remember { mutableStateOf("") }
        Row {
            TextField(
                value = input,
                onValueChange = { input = it },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    onCardClick(input)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        RssList(rssList = rssList, onCardClick = onCardClick)
    }
}

@Composable
fun RssList(
    rssList: List<Rss>,
    onCardClick: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(rssList) { rss ->
            RssCard(
                rss = rss,
                onCardClick = onCardClick,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}