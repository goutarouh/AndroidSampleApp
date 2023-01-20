package com.github.goutarouh.androidsampleapp.core.ui.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.github.goutarouh.androidsampleapp.core.ui.R

@Composable
internal fun ConfirmDialog(
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    confirmButtonColor: Color,
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
                text = stringResource(id = R.string.alert_dialog_delete_confirm),
                confirmButtonColor = confirmButtonColor
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
    confirmButtonColor: Color,
) {
    TextButton(onClick = onConfirm) {
        Text(
            text = text,
            color = confirmButtonColor
        )
    }
}

@Composable
private fun DismissButton(
    onDismiss: () -> Unit,
    text: String
) {
    TextButton(onClick = onDismiss) {
        Text(text = text)
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