package com.github.goutarouh.androidsampleapp

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.mutableStateOf

class MainUiState(
    scaffoldState: ScaffoldState
) {
    val isTopBarShow = mutableStateOf(true)
    val isBottomBarShow = mutableStateOf(true)
}