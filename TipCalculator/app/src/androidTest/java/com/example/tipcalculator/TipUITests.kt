package com.example.tipcalculator

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculateTipWith20PercentWithoutRoundUp_showsTipAmountWithoutRounding() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipTimeLayout()
            }
        }
        composeTestRule.onNodeWithText("Bill amount").performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage").performTextReplacement("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with that text was found(Without rounding)")
    }

    @Test
    fun calculateTipWith20PercentWithRounding_showsTipAmountRounding() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipTimeLayout()
            }
        }

        composeTestRule.onNodeWithText("Bill amount").performTextInput("23")
        composeTestRule.onNodeWithText("Tip Percentage").performTextReplacement("20")
        composeTestRule.onNodeWithContentDescription("Switch button Round Up?").performClick()
        val expectedTip = NumberFormat.getCurrencyInstance().format(5)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with that text was found(With rounding)")
    }
}