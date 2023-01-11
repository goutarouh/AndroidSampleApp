package com.github.goutarouh.androidsampleapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.goutarouh.androidsampleapp.feature.featurea.RSS_HOME_ROUTE
import com.github.goutarouh.androidsampleapp.feature.featurea.rssHome
import com.github.goutarouh.androidsampleapp.feature.featureb.featureBScreen
import com.github.goutarouh.androidsampleapp.feature.featurec.featureCScreen

@Composable
fun MainNavigation(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = RSS_HOME_ROUTE) {
        rssHome(navController)
        featureBScreen() {
            navController.navigate(RSS_HOME_ROUTE)
        }
        featureCScreen()
    }
}