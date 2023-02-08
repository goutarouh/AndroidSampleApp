package com.github.goutarouh.simplerssreader.core.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.goutarouh.simplerssreader.core.ui.R

@Composable
internal fun ConfirmDialog(
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = {
            AlertDialogTitle(title = title)
        },
        text = {
            AlertDialogText(text = text)
        },
        onDismissRequest = {},
        confirmButton = {
            ConfirmButton(
                onConfirm = onConfirm,
                text = stringResource(id = R.string.alert_dialog_delete_confirm)
            )
        },
        dismissButton = {
            DismissButton(
                onDismiss = onDismiss,
                text = stringResource(id = R.string.alert_dialog_delete_cancel)
            )
        }
    )
}

@Composable
private fun ConfirmButton(
    onConfirm: () -> Unit,
    text: String,
) {
    TextButton(onClick = onConfirm) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun DismissButton(
    onDismiss: () -> Unit,
    text: String
) {
    TextButton(onClick = onDismiss) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun AlertDialogTitle(
    title: String
) {
    Text(text = title)
}

@Composable
private fun AlertDialogText(text: String) {
    Text(text = text)
}