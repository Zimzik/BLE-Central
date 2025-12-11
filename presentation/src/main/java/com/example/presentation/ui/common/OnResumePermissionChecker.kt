package com.example.presentation.ui.common

import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun OnResumePermissionsChecker(
    permissions: List<String>,
    onPermissionsGranted: (Boolean) -> Unit,
) {
    val context = LocalContext.current

    OnResumeComposeListener {
        val isGranted = permissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
        onPermissionsGranted(isGranted)
    }
}