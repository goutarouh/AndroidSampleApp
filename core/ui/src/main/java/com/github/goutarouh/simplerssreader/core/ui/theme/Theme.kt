package com.github.goutarouh.simplerssreader.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
private val DarkColorPalette = darkColors(
    primary = BlueGray900,
    onPrimary = BlueGray100,
    secondary = BlueGray900,
    onSecondary = BlueGray100,
    background = Black,
    onSurface = White,
    error = Red300
)

private val LightColorPalette = lightColors(
    primary = BlueGray100,
    onPrimary = BlueGray900,
    secondary = BlueGray100,
    onSecondary = BlueGray900,
    background = White,
    onSurface = Black,
    error = Red600
)

@Composable
fun SrrTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

}