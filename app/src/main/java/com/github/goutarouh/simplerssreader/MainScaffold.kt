package com.github.goutarouh.simplerssreader

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.goutarouh.simplerssreader.navigation.MainNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    navController: NavHostController
) {
    Scaffold { paddingValues ->
        MainNavigation(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}