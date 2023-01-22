package com.github.goutarouh.simplerssreader

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.goutarouh.simplerssreader.navigation.MainNavigation

@Composable
fun MainScaffold(
    mainUiState: MainUiState,
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {
    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        MainNavigation(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}