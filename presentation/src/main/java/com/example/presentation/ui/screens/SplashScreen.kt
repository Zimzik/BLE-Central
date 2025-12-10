package com.example.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.common.RequiredPermissions
import com.example.presentation.permission.PermissionStateWrapper
import com.example.presentation.permission.PermissionStateWrapperImpl
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(
    onPermissionsGranted: () -> Unit,
    onPermissionsDenied: () -> Unit
) {

    val permissionState = rememberMultiplePermissionsState(RequiredPermissions.BLE)
    val wrapper = PermissionStateWrapperImpl(permissionState)

    SplashScreenContent(
        permissionState = wrapper,
        onPermissionsGranted = onPermissionsGranted,
        onPermissionsDenied = onPermissionsDenied
    )

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreenContent(
    permissionState: PermissionStateWrapper,
    onPermissionsGranted: () -> Unit,
    onPermissionsDenied: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Bluetooth,
            contentDescription = stringResource(R.string.splash_icon_description),
            modifier = Modifier.size(100.dp)
        )
    }

    LaunchedEffect(Unit) {
        if (permissionState.allPermissionGranted) {
            onPermissionsGranted()
        } else {
            onPermissionsDenied()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SplashScreenPreview() {
    SplashScreen({}, {})
}