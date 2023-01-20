package com.github.goutarouh.androidsampleapp.core.ui.dialog

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.goutarouh.androidsampleapp.core.ui.R

@Composable
fun DeleteConfirmDialog(
    deleteTargetName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    ConfirmDialog(
        title = stringResource(id = R.string.alert_dialog_delete_title),
        text = stringResource(id = R.string.alert_dialog_delete_text, deleteTargetName),
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        confirmButtonColor = MaterialTheme.colors.error
    )

}