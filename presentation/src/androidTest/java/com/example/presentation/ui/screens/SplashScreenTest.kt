package com.example.presentation.ui.screens

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.runComposeUiTest
import com.example.presentation.permission.PermissionStateWrapper
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class SplashScreenTest {

    private lateinit var wrapper: PermissionStateWrapper

    @Before
    fun setup() {
        wrapper = mockk<PermissionStateWrapper>()
    }

    @Test
    fun splashScreenIconDisplayed() = runComposeUiTest {
        setContent {
            SplashScreen({}, {})
        }

        onNodeWithContentDescription("Splash icon")
            .assertIsDisplayed()
    }

    @Test
    fun whenAllGranted_callOnPermissionGranted() = runComposeUiTest {
        every { wrapper.allPermissionGranted } returns true
        var onPermissionsGrantedCalled = false
        setContent {
            SplashScreenContent(
                permissionState = wrapper,
                onPermissionsGranted = { onPermissionsGrantedCalled = true },
                onPermissionsDenied = {}
            )
        }
        waitForIdle()
        assertTrue(onPermissionsGrantedCalled)
    }

    @Test
    fun whenAllDenied_callOnPermissionDenied() = runComposeUiTest {
        every { wrapper.allPermissionGranted } returns false
        var onPermissionsDeniedCalled = false
        setContent {
            SplashScreenContent(
                permissionState = wrapper,
                onPermissionsGranted = {},
                onPermissionsDenied = { onPermissionsDeniedCalled = true }
            )
        }
        waitForIdle()
        assertTrue(onPermissionsDeniedCalled)
    }
}