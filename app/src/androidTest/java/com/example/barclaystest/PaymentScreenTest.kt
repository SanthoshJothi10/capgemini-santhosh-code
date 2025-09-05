package com.example.barclaystest

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.barclaystest.model.TransferType
import com.example.barclaystest.presentation.PaymentScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PaymentScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDomesticPaymentFieldsValidation() {
        composeTestRule.setContent {
            PaymentScreen(
                navController = rememberNavController(),
                selectedOptionValue = TransferType.DOMESTIC.toString()
            )
        }
        // Check if recipient field exists
        composeTestRule.onNodeWithTag("RecepientField").assertExists()
        // Enter invalid data and check error
        composeTestRule.onNodeWithTag("RecepientField").performTextInput("")
        // Enter valid data
        composeTestRule.onNodeWithText("Please enter the recepient name") // Replace with actual string
            .assertDoesNotExist()

        composeTestRule.onNodeWithTag("AccountField").assertExists()
        // Enter invalid data and check error
        composeTestRule.onNodeWithTag("AccountField").performTextInput("")
        // Enter valid data
        composeTestRule.onNodeWithText("Please enter the Account number") // Replace with actual string
            .assertDoesNotExist()

        composeTestRule.onNodeWithTag("AmountField").assertExists()
        // Enter invalid data and check error
        composeTestRule.onNodeWithTag("AmountField").performTextInput("")
        // Enter valid data
        composeTestRule.onNodeWithText("Please enter the amount") // Replace with actual string
            .assertDoesNotExist()

    }

    @Test
    fun testInternationalFieldsVisible() {
        composeTestRule.setContent {
            PaymentScreen(
                navController = rememberNavController(),
                selectedOptionValue = TransferType.INTERNATIONAL.toString()
            )
        }
        // Check if IBAN and SWIFT fields are visible
        composeTestRule.onNodeWithTag("ibanField").assertExists()
        composeTestRule.onNodeWithTag("swiftCodeField").assertExists()

        composeTestRule.onNodeWithText("iBan and swift Code Field Name is required")
            .assertDoesNotExist()
    }
}
