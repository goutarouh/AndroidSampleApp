package com.github.goutarouh.androidsampleapp.core.ui.composable

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebView(
    url: String,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = ::WebView,
        update = { webView ->
            webView.webViewClient = WebViewClient()
            webView.loadUrl(url)
        },
        modifier = modifier
    )
}