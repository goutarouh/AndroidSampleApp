package com.github.goutarouh.androidsampleapp.feature.rss.rsshome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.goutarouh.androidsampleapp.core.repository.model.rss.Rss
import com.github.goutarouh.androidsampleapp.feature.rss.rsshome.RssHomeScreenUiState.*
import com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist.RssItemListScreen
import com.github.goutarouh.androidsampleapp.feature.rss.rssitemlist.RssItemScreenAction

const val RSS_HOME_ROUTE = "RSS_HOME_ROUTE"
const val RSS_LIST_ROUTE = "RSS_LIST_ROUTE"

fun NavGraphBuilder.rssHome(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(route = RSS_HOME_ROUTE) {
        RssHomeScreen(
            navigateTo = { navController.navigate(RSS_LIST_ROUTE) },
            modifier = modifier
        )
    }
    composable(route = RSS_LIST_ROUTE) {
        RssItemListScreen(
            rssItemScreenAction = object: RssItemScreenAction {
                override fun navigateBack() { navController.popBackStack() }
                override fun onCardClick(link: String) {}
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
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
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
                    RssList(
                        rssList = state.rssList,
                        onCardClick = navigateTo
                    )
                }
            }
        }
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