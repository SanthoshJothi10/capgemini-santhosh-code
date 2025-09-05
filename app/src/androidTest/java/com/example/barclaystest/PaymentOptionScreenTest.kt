package com.example.barclaystest

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.barclaystest.presentation.PaymentOptionScreen

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PaymentOptionScreenTest() {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Composable
    @Test
    fun TestRadioButtonsAndNavigation() {
        composeTestRule.setContent {
            // Replace with your actual composable
            PaymentOptionScreen(navController = rememberNavController())
        }


        // Check if both options are displayed
        composeTestRule.onNodeWithText(stringResource(R.string.domestic)).assertExists()
        composeTestRule.onNodeWithText(stringResource(R.string.internation)).assertExists()


        // Click Domestic Transfer and verify navigation
        composeTestRule.onNodeWithText(stringResource(R.string.domestic)).performClick()
        // You can verify navigation or state change here if needed

        // Click International Transfer
        composeTestRule.onNodeWithText(stringResource(R.string.internation)).performClick()

    }
}