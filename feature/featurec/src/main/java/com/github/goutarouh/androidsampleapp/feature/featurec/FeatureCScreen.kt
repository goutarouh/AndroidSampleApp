package com.github.goutarouh.androidsampleapp.feature.featurec

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val featureCRoute = "feature_c_route"

fun NavGraphBuilder.featureCScreen() {
    composable(route = featureCRoute) {
        FeatureCScreen()
    }
}

@Composable
fun FeatureCScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("FeatureCScreen")
    }
}