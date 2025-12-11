package com.example.presentation.ui.common

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.R

@Composable
fun PermanentDeniedPermissionsDialog(
    title: String,
    text: String,
    onDismiss: () -> Unit,
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = text) },
        confirmButton = {
            TextButton(onClick = {
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", context.packageName, null)
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                onDismiss()
            }) {
                Text(text = stringResource(R.string.OK))
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun PermanentDeniedPermissionsDialogPreview() {
    PermanentDeniedPermissionsDialog(
        title = stringResource(R.string.request_permission),
        text = stringResource(R.string.permission_requirement_description),
        onDismiss = {}
    )
}