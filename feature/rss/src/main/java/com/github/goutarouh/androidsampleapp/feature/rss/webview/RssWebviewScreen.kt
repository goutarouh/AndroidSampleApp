package com.github.goutarouh.androidsampleapp.feature.rss.webview

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.goutarouh.androidsampleapp.core.ui.composable.WebView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RssWebViewScreen(
    rssLink: String,
    navigationBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    WebView(
        url = rssLink,
        modifier = modifier
    )
}