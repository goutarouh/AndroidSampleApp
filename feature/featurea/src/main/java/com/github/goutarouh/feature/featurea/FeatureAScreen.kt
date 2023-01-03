package com.github.goutarouh.feature.featurea

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val featureARoute = "feature_a_route"

fun NavGraphBuilder.featureAScreen(
    navigateToA: () -> Unit
) {
    composable(route = featureARoute) {
        FeatureAScreen()
    }
}

@Composable
fun FeatureAScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("FeatureAScreen")
    }
}