package com.example.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.common.RequiredPermissions
import com.example.presentation.ui.common.OnResumePermissionsChecker

@Composable
fun ScanScreen(
    onBlePermissionDenied: () -> Unit
) {
    ScanScreenContent(onBlePermissionDenied)
}

@Composable
fun ScanScreenContent(onBlePermissionDenied: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Scan screen")
    }

    OnResumePermissionsChecker(RequiredPermissions.BLE) { granted ->
        if (!granted) onBlePermissionDenied()
    }
}

@Composable
@Preview(showBackground = true)
fun ScanScreenPreview() {
    ScanScreenContent({})
}