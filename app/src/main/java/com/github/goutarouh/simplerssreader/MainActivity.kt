package com.github.goutarouh.simplerssreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.github.goutarouh.simplerssreader.core.ui.theme.SrrTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SrrTheme {

                UpdateSystemBarsColor()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val scaffoldState = rememberScaffoldState()
                    val navController = rememberNavController()
                    val mainUiState = remember {
                        MainUiState(scaffoldState)
                    }

                    MainScaffold(
                        mainUiState = mainUiState,
                        scaffoldState = scaffoldState,
                        navController = navController
                    )
                }
            }
        }
    }

    @Composable
    private fun UpdateSystemBarsColor() {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()
        val color = MaterialTheme.colors.background

        DisposableEffect(systemUiController, useDarkIcons) {
            systemUiController.setSystemBarsColor(
                color = color,
                darkIcons = useDarkIcons
            )

            onDispose {}
        }
    }
}