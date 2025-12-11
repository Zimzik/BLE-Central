package com.example.presentation.permission

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalPermissionsApi::class)
class PermissionStateWrapperUnitTest {

    private lateinit var permissionState: MultiplePermissionsState

    @Before
    fun setup() {
        permissionState = mockk<MultiplePermissionsState>()
    }

    @Test
    fun `PermissionStateWrapper returns true when allPermissionsGranted is true`() {
        val wrapper = PermissionStateWrapperImpl(permissionState)
        every { permissionState.allPermissionsGranted } returns true
        assertTrue(wrapper.allPermissionGranted)
    }

    @Test
    fun `PermissionStateWrapper returns false when allPermissionsGranted is false`() {
        val wrapper = PermissionStateWrapperImpl(permissionState)
        every { permissionState.allPermissionsGranted } returns false
        assertFalse(wrapper.allPermissionGranted)
    }
}