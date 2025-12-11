package com.example.presentation.ui.common

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.Lifecycle
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class OnResumePermissionsCheckerTest {

    private val permission1 = "permission1"
    private val permission2 = "permission2"

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun permissionsGrantedReturnsTrue() {
        val fakeContext = mockk<Context> {
            every { checkPermission(permission1, any(), any()) } returns PackageManager.PERMISSION_GRANTED
            every { checkPermission(permission2, any(), any()) } returns PackageManager.PERMISSION_GRANTED
        }

        var result = false
        composeRule.setContent {
            CompositionLocalProvider(LocalContext provides fakeContext) {
                OnResumePermissionsChecker(listOf(permission1, permission2)) { isGranted ->
                    result = isGranted
                }
            }
        }

        composeRule.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        assertTrue(result)
    }

    @Test
    fun permissionsDeniedReturnsFalse() {
        val fakeContext = mockk<Context> {
            every { checkPermission(permission1, any(), any()) } returns PackageManager.PERMISSION_DENIED
            every { checkPermission(permission2, any(), any()) } returns PackageManager.PERMISSION_DENIED
        }

        var result = true
        composeRule.setContent {
            CompositionLocalProvider(LocalContext provides fakeContext) {
                OnResumePermissionsChecker(listOf(permission1, permission2)) { isGranted ->
                    result = isGranted
                }
            }
        }

        composeRule.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        assertFalse(result)
    }

    @Test
    fun onePermissionDeniedReturnsFalse() {
        val fakeContext = mockk<Context> {
            every { checkPermission(permission1, any(), any()) } returns PackageManager.PERMISSION_GRANTED
            every { checkPermission(permission2, any(), any()) } returns PackageManager.PERMISSION_DENIED
        }

        var result = true
        composeRule.setContent {
            CompositionLocalProvider(LocalContext provides fakeContext) {
                OnResumePermissionsChecker(listOf(permission1, permission2)) { isGranted ->
                    result = isGranted
                }
            }
        }

        composeRule.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        assertFalse(result)
    }

}