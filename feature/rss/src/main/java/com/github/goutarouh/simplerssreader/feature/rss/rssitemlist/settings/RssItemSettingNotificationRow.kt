package com.github.goutarouh.simplerssreader.feature.rss.rssitemlist.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.goutarouh.simplerssreader.core.ui.annotation.DayAndNightPreviews
import com.github.goutarouh.simplerssreader.core.ui.theme.SrrTheme
import com.github.goutarouh.simplerssreader.feature.rss.R

@Composable
fun RssItemSettingNotificationRow(
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.rss_settings_notification),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h4
            )
            Switch(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }
        Text(
            text = stringResource(id = R.string.rss_settings_notification_description),
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.caption
        )
    }
}

@DayAndNightPreviews
@Composable
fun PreviewRssItemSettingNotificationRow() {
    SrrTheme {
        Surface {
            RssItemSettingNotificationRow()
        }
    }
}