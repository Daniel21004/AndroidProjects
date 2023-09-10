package com.example.tipcalculator

import org.junit.Assert
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTest {

    // The test methods must indicates: What does the test? and What is the expected result?
    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 10.00
        val tipPercent = 20.00
        // Remember that the original method returns a value formatted in the local currency
        val expectedTip = NumberFormat.getCurrencyInstance().format(2) // 2 because 10 % 20 = 2
        // actual = real
        // I don't need to import the calculateTip method, because it is annotated with @VisibleForTest
        val actualTip = calculateTip(amount, tipPercent, false)

        Assert.assertEquals(
            "Calculate tip with the 20 percent of tip without Rounding",
            expectedTip,
            actualTip
        )
    }

    @Test
    fun calculateTip_20PercentWithRoundUp() {
        val amount = 23.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(5)
        val actualTip = calculateTip(amount, tipPercent, true)

        Assert.assertEquals(
            "Calculate tip with the 20 percent of tip and with rounding",
            expectedTip,
            actualTip
        )
    }
}