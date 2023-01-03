package com.github.goutarouh.feature.featureb

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val featureBRoute = "feature_b_route"

fun NavGraphBuilder.featureBScreen(
    navigateToA: () -> Unit
) {
    composable(route = featureBRoute) {
        FeatureBScreen()
    }
}

@Composable
fun FeatureBScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("FeatureBScreen")
    }
}