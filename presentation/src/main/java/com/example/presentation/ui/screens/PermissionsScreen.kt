package com.example.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.common.RequiredPermissions
import com.example.presentation.ui.common.OnResumePermissionsChecker
import com.example.presentation.ui.common.PermanentDeniedPermissionsDialog
import com.example.presentation.ui.common.PermissionRequester
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@Composable
fun PermissionsScreen(
    onPermissionGranted: () -> Unit
) {
    PermissionsScreenContent(onPermissionGranted)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreenContent(
    onPermissionGranted: () -> Unit,
) {

    var shouldShowPermanentDeniedPermissionDialog by remember { mutableStateOf(false) }

    PermissionRequester(
        permissions = RequiredPermissions.BLE.toTypedArray(),
        onPermissionsGranted = {
            onPermissionGranted()
        },
        onPermissionsDenied = {},
        onPermanentDenied = {
            shouldShowPermanentDeniedPermissionDialog = true
        }
    ) { requestPermissionAction ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.width(280.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.permission_requirement_description),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    requestPermissionAction()
                }) {
                    Text(stringResource(R.string.request_permission))
                }
            }
        }
    }

    OnResumePermissionsChecker(RequiredPermissions.BLE) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        }
    }

    if (shouldShowPermanentDeniedPermissionDialog) {
        PermanentDeniedPermissionsDialog(
            text = stringResource(R.string.permission_require_changes_in_settings),
            title = stringResource(R.string.request_permission),
            onDismiss = {
                shouldShowPermanentDeniedPermissionDialog = false
            }
        )
    }
}



@Composable
@Preview(showBackground = true)
fun PermissionsScreenPreview() {
    PermissionsScreenContent({})
}