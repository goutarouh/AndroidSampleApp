package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun RssItemSettingDialog(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onDismiss() }
        ) {
            RssItemSettingContents(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun RssItemSettingContents(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 8.dp, vertical = 32.dp)
            .clickable { }
    ) {
        Column {
            RssItemSettingAutoUpdateRow()
            Spacer(modifier = Modifier.height(32.dp))
            RssItemSettingNotificationRow()
        }
    }
}