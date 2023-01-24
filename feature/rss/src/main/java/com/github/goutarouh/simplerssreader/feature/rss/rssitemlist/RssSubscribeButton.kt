package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import com.github.goutarouh.simplerssreader.core.ui.theme.SrrTheme
import com.github.goutarouh.simplerssreader.feature.rss.R
import java.time.LocalDateTime

@Composable
fun RssSubscribeButton(
    rss: Rss,
    modifier: Modifier = Modifier,
    setAutoFetch: (String, Boolean) -> Unit
) {

    var isAutoFetch by remember { mutableStateOf(rss.isAutoFetch) }

    OutlinedButton(
        onClick = {
            val newAutoRenew = !isAutoFetch
            isAutoFetch = newAutoRenew
            setAutoFetch(rss.rssLink, newAutoRenew)
        },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isAutoFetch) MaterialTheme.colors.onBackground else MaterialTheme.colors.background
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.rss_subscribe),
            color = if (isAutoFetch) MaterialTheme.colors.background else MaterialTheme.colors.onBackground,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, backgroundColor = 0xFFFFFF)
@Composable
fun PreviewRssSubscribeButtonNightYes() {
    val rss = Rss("", "", "", listOf(), true, LocalDateTime.now())
    SrrTheme() {
        RssSubscribeButton(rss = rss, setAutoFetch = { _, _ -> })
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, backgroundColor = 0x000000)
@Composable
fun PreviewRssSubscribeButtonNightNo() {
    val rss = Rss("", "", "", listOf(), true, LocalDateTime.now())
    SrrTheme() {
        RssSubscribeButton(rss = rss, setAutoFetch = { _, _ -> })
    }
}