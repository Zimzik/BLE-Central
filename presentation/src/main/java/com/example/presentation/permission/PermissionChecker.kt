package com.example.presentation.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import javax.inject.Inject

class PermissionChecker @Inject constructor(
    private val context: Context
) {
    fun isPermissionsGranted(permissions: List<String>) =
        permissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
}