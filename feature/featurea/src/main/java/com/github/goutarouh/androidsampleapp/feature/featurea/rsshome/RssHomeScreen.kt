package com.github.goutarouh.androidsampleapp.feature.featurea

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.goutarouh.androidsampleapp.feature.featurea.rssitemlist.RssItemListScreen
import com.github.goutarouh.androidsampleapp.feature.featurea.rssitemlist.RssItemScreenAction

const val RSS_HOME_ROUTE = "RSS_HOME_ROUTE"
const val RSS_LIST_ROUTE = "RSS_LIST_ROUTE"

fun NavGraphBuilder.rssHome(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(route = RSS_HOME_ROUTE) {
        RssHomeScreen() {
            navController.navigate(RSS_LIST_ROUTE)
        }
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
    navigateTo: (String) -> Unit
) {
    // TODO 購読しているRSSを表示する
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "RSS LIST")
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Zenn Android (TODO)",
                modifier = Modifier.clickable { navigateTo("") }
            )
        }
    }
}