package com.example.presentation.permission

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
class PermissionStateWrapperImpl(
    private val permissionState: MultiplePermissionsState
) : PermissionStateWrapper {
    override val allPermissionGranted: Boolean
        get() = permissionState.allPermissionsGranted
}