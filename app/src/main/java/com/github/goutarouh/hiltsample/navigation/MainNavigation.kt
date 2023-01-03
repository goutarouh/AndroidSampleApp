package com.github.goutarouh.hiltsample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.goutarouh.feature.featurea.featureARoute
import com.github.goutarouh.feature.featurea.featureAScreen
import com.github.goutarouh.feature.featureb.featureBRoute
import com.github.goutarouh.feature.featureb.featureBScreen

@Composable
fun MainNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = featureARoute) {
        featureAScreen() {
            navController.navigate(featureBRoute)
        }
        featureBScreen() {
            navController.navigate(featureARoute)
        }
    }
}