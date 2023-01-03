package com.github.goutarouh.hiltsample

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.github.goutarouh.hiltsample.navigation.MainNavigation

@Composable
fun MainScaffold(
    mainUiState: MainUiState,
    scaffoldState: ScaffoldState,
    navController: NavHostController
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (mainUiState.isTopBarShow.value) {
                MainTopBar("App")
            }
        },
        bottomBar = {
            if (mainUiState.isBottomBarShow.value) {
                MainBottomNavigation() {
                    navController.navigate(it)
                }
            }
        }
    ) {
        MainNavigation(navController)
    }
}