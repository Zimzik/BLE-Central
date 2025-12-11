package com.example.presentation.ui.common

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat

@Composable
fun PermissionRequester(
    permissions: Array<String>,
    onPermissionsGranted: () -> Unit,
    onPermissionsDenied: () -> Unit,
    onPermanentDenied: () -> Unit,
    content: @Composable (onPermissionsRequestAction: () -> Unit) -> Unit
) {
    val context = LocalContext.current
    val activity = context as Activity

    val requestPermissionContract = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val denied = permissionsMap.filterValues { !it }.keys
        if (denied.isEmpty()) {
            onPermissionsGranted()
        } else {
            val permanentlyDenied = denied.filter { permission ->
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
            }
            if (permanentlyDenied.isNotEmpty()) {
                onPermanentDenied()
            } else {
                onPermissionsDenied()
            }
        }
    }

    content {
        requestPermissionContract.launch(permissions)
    }
}