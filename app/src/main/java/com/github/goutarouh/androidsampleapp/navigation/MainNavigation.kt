package com.github.goutarouh.androidsampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.goutarouh.androidsampleapp.feature.rss.rsshome.RSS_HOME_ROUTE
import com.github.goutarouh.androidsampleapp.feature.rss.rsshome.rssHome

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = RSS_HOME_ROUTE) {
        rssHome(navController, modifier)
    }
}