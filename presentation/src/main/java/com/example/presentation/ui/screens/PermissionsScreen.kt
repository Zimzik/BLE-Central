package com.example.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PermissionsScreen(
    onPermissionGranted: () -> Unit
) {
    PermissionsScreenContent(onPermissionGranted)
}

@Composable
fun PermissionsScreenContent(
    onPermissionGranted: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Permissions screen")
    }
}

@Composable
@Preview(showBackground = true)
fun PermissionsScreenPreview() {
    PermissionsScreenContent({})
}