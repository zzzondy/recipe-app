package com.recipe_adding.screens.recipe_adding.states.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.test.performTextInput
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.RecipeAddingScreenContentState
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.RecipeAddingScreenContentStateSections
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.RecipeAddingScreenContentStateTestingTags
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.DescriptionSectionTestingTags
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.NameSectionTestingTags
import com.recipeapp.theme.RecipeAppTheme
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class RecipeAddingScreenContentStateTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun enterRecipeName_showRecipeName(): Unit = with(rule) {
        val inputText = "Beautiful"

        setContent {
            RecipeAppTheme {
                var recipeName by remember { mutableStateOf("") }

                RecipeAddingScreenContentState(
                    images = emptyList(),
                    recipeName = recipeName,
                    cookingTime = "",
                    mealTypes = listOf(
                        MealType(name = "Some type", isEditable = true)
                    ),
                    description = "",
                    ingredients = emptyList(),
                    onChangedIngredient = { _, _ -> },
                    onChangedIngredientQuantity = { _, _ -> },
                    onChangedName = {
                        recipeName = it
                    }
                )
            }
        }

        val recipeNameTextField = onNodeWithTag(NameSectionTestingTags.RECIPE_NAME_TEXT_FIELD_TAG)

        recipeNameTextField.performTextInput(inputText)

        Assert.assertEquals(
            inputText,
            recipeNameTextField.fetchSemanticsNode().config[SemanticsProperties.EditableText].text
        )
    }

    @Test
    fun enterRecipeName_showClearButton_clearText(): Unit = with(rule) {
        val inputText = "Beautiful"

        setContent {
            RecipeAppTheme {
                var recipeName by remember { mutableStateOf("") }

                RecipeAddingScreenContentState(
                    images = emptyList(),
                    recipeName = recipeName,
                    cookingTime = "",
                    mealTypes = listOf(
                        MealType(name = "Some type", isEditable = true)
                    ),
                    description = "",
                    ingredients = emptyList(),
                    onChangedIngredient = { _, _ -> },
                    onChangedIngredientQuantity = { _, _ -> },
                    onChangedName = {
                        recipeName = it
                    }
                )
            }
        }

        val recipeNameTextField = onNodeWithTag(NameSectionTestingTags.RECIPE_NAME_TEXT_FIELD_TAG)
        val clearButton = onNodeWithTag(NameSectionTestingTags.CLEAR_BUTTON, useUnmergedTree = true)

        recipeNameTextField.performTextInput(inputText)

        clearButton.assertIsDisplayed()

        clearButton.performClick()

        Assert.assertEquals(
            "",
            recipeNameTextField.fetchSemanticsNode().config[SemanticsProperties.EditableText].text
        )
    }

    @Test
    fun enterDescription_showDescription(): Unit = with(rule) {
        val inputText = "Beautiful"

        setContent {
            RecipeAppTheme {
                var description by remember { mutableStateOf("") }

                RecipeAddingScreenContentState(
                    images = emptyList(),
                    recipeName = "",
                    cookingTime = "",
                    mealTypes = listOf(
                        MealType(name = "Some type", isEditable = true)
                    ),
                    description = description,
                    ingredients = emptyList(),
                    onChangedIngredient = { _, _ -> },
                    onChangedIngredientQuantity = { _, _ -> },
                    onDescriptionChanged = {
                        description = it
                    }
                )
            }
        }

        val descriptionTextField =
            onNodeWithTag(
                DescriptionSectionTestingTags.DESCRIPTION_TEXT_FIELD_TAG,
                useUnmergedTree = true
            )

        onNodeWithTag(RecipeAddingScreenContentStateTestingTags.LAZY_COLUMN_TAG)
            .performScrollToKey(RecipeAddingScreenContentStateSections.DESCRIPTION_SECTION)

        descriptionTextField.performTextInput(inputText)

        Assert.assertEquals(
            inputText,
            descriptionTextField.fetchSemanticsNode().config[SemanticsProperties.EditableText].text
        )
    }
}