package com.example.presentation.ui.common

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.runComposeUiTest
import androidx.lifecycle.Lifecycle
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OnResumeComposeListenerTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun onResumeComposeListener() {
        var callbackValue: Boolean? = null
        composeRule.setContent {
            OnResumeComposeListener {
                callbackValue = true
            }
        }

        composeRule.activityRule.scenario.moveToState(Lifecycle.State.RESUMED)
        assertNotNull(callbackValue)
    }

}