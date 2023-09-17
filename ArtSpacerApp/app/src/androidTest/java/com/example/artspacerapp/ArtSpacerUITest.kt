package com.example.artspacerapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.artspacerapp.domain.Card
import com.example.artspacerapp.ui.theme.ArtSpacerAppTheme
import org.junit.Rule
import org.junit.Test

class ArtSpacerUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickOnRightButton_shouldChangeToNextCard(){
        composeTestRule.setContent {
            ArtSpacerAppTheme {
                ArtSpacerLayout()
            }
        }

        val buttonContentDescription = "Arrow right"
        val expectedCard = Card(R.drawable.calcifer, "Calcifer", "Mampa", 2000)
        val notFoundMessage = "No node with that text was found (Right button)"
        val shapeContentDescription = "Circle shape"

        composeTestRule.onNodeWithContentDescription(buttonContentDescription).performClick()

        composeTestRule.onNodeWithContentDescription(expectedCard.title).assertExists(notFoundMessage)
        composeTestRule.onNodeWithText(expectedCard.title).assertExists(notFoundMessage)
        composeTestRule.onNodeWithText(expectedCard.author).assertExists(notFoundMessage)
        composeTestRule.onNodeWithContentDescription(shapeContentDescription).assertExists(notFoundMessage)
        composeTestRule.onNodeWithText("(${expectedCard.year})").assertExists(notFoundMessage)
    }

    @Test
    fun clickOnLeftButton_shouldChangeToPreviousCard(){
        composeTestRule.setContent {
            ArtSpacerAppTheme {
                ArtSpacerLayout()
            }
        }

        val buttonContentDescription = "Arrow left"
        val expectedCard = Card(R.drawable.archer_boy_minimalista, "Minimalist Boy", "Unkown", 2012)
        val notFoundMessage = "No node with that text was found (Left Button)"
        val shapeContentDescription = "Circle shape"

        composeTestRule.onNodeWithContentDescription(buttonContentDescription).performClick()

        composeTestRule.onNodeWithContentDescription(expectedCard.title).assertExists(notFoundMessage)
        composeTestRule.onNodeWithText(expectedCard.title).assertExists(notFoundMessage)
        composeTestRule.onNodeWithText(expectedCard.author).assertExists(notFoundMessage)
        composeTestRule.onNodeWithContentDescription(shapeContentDescription).assertExists(notFoundMessage)
        composeTestRule.onNodeWithText("(${expectedCard.year})")
    }
}