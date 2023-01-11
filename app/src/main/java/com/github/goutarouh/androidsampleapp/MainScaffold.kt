package com.github.goutarouh.androidsampleapp

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.github.goutarouh.androidsampleapp.navigation.MainNavigation

@Composable
fun MainScaffold(
    mainUiState: MainUiState,
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (mainUiState.isBottomBarShow.value) {
                MainBottomNavigation() {
                    navController.navigate(it)
                }
            }
        }
    ) { paddingValues ->
        MainNavigation(paddingValues, navController)
    }
}