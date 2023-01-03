package com.github.goutarouh.androidsampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.goutarouh.androidsampleapp.feature.featurea.featureARoute
import com.github.goutarouh.androidsampleapp.feature.featurea.featureAScreen
import com.github.goutarouh.androidsampleapp.feature.featureb.featureBRoute
import com.github.goutarouh.androidsampleapp.feature.featureb.featureBScreen
import com.github.goutarouh.androidsampleapp.feature.featurec.featureCScreen

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
        featureCScreen()
    }
}