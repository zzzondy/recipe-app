package com.recipeapp.components.screen_states_ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.recipeapp.theme.RecipeAppTheme
import org.junit.Rule
import org.junit.Test

class ErrorScreenStateTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun shouldShowTheTextThatWasTransmitted(): Unit = with(rule) {
        val transmittedPrimaryText = "Some big error"
        val transmittedSecondaryText = "Sorry we are working on this problem"

        setContent {
            RecipeAppTheme {
                ErrorScreenState(
                    title = transmittedPrimaryText,
                    subtitle = transmittedSecondaryText
                )
            }
        }

        onNodeWithTag(ErrorScreenStateTestingTags.PRIMARY_TEXT).assertTextContains(
            transmittedPrimaryText
        )

        onNodeWithTag(ErrorScreenStateTestingTags.SECONDARY_TEXT).assertTextContains(
            transmittedSecondaryText
        )
    }
}