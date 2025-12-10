package com.example.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigation.Destination.PERMISSION_SCREEN
import com.example.presentation.navigation.Destination.SCAN_SCREEN
import com.example.presentation.navigation.Destination.SPLASH_SCREEN
import com.example.presentation.ui.screens.PermissionsScreen
import com.example.presentation.ui.screens.ScanScreen
import com.example.presentation.ui.screens.SplashScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainNavGraph(innerPaddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.padding(innerPaddingValues),
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        composable(SPLASH_SCREEN) {
            SplashScreen(
                onPermissionsGranted = {
                    navController.navigate(SCAN_SCREEN) {
                        popUpTo(SPLASH_SCREEN) { inclusive = true }
                    }
                },
                onPermissionsDenied = {
                    navController.navigate(PERMISSION_SCREEN) {
                        popUpTo(SPLASH_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(PERMISSION_SCREEN) {
            PermissionsScreen {
                navController.navigate(SCAN_SCREEN) {
                    popUpTo(PERMISSION_SCREEN) { inclusive = true }
                }
            }
        }

        composable(SCAN_SCREEN) {
            ScanScreen()
        }
    }
}