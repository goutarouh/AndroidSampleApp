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
import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import com.github.goutarouh.simplerssreader.feature.rss.rssitemlist.RssItemSettingAction

@Composable
fun RssItemSettingDialog(
    rss: Rss,
    rssItemSettingAction: RssItemSettingAction,
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
                rss = rss,
                rssItemSettingAction = rssItemSettingAction,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    // 通知機能の権限はUI調整が完了するまでpending
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//        val permissionState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
//        when {
//            permissionState.hasPermission -> {}
//            permissionState.shouldShowRationale -> {
//                AlertDialog(
//                    text = {},
//                    onDismissRequest = {},
//                    confirmButton = {
//                        TextButton(onClick = { permissionState.launchPermissionRequest() }) {
//                            Text(text = "OK")
//                        }
//                    }
//                )
//            }
//            permissionState.permissionRequested -> {}
//            else -> {
//                SideEffect {
//                    permissionState.launchPermissionRequest()
//                }
//            }
//        }
//    }
}

@Composable
fun RssItemSettingContents(
    rss: Rss,
    rssItemSettingAction: RssItemSettingAction,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 4.dp, vertical = 32.dp)
            .clickable { }
    ) {
        Column {
            RssItemSettingAutoUpdateRow(rss.isAutoFetch) { isAutoFetch ->
                rssItemSettingAction.setAutoFetch(rss.rssLink, isAutoFetch)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // 通知機能はいったんpending
//            RssItemSettingNotificationRow(
//                isPushNotification = rss.isPushNotification,
//                isAutoFetch = rss.isAutoFetch
//            ) { isPushNotification ->
//                rssItemSettingAction.setNotificationEnabled(rss.rssLink, isPushNotification)
//            }
        }
    }
}