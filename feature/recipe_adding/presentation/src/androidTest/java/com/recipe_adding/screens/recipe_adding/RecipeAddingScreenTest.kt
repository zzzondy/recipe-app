package com.recipe_adding.screens.recipe_adding

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.presentation.R
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreen
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreenContent
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenState
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.RecipeAddingScreenContentStateTestingTags
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.RecipeAddingScreenLoadingStateTestingTags
import com.recipeapp.components.screen_states_ui.screens.ErrorScreenStateTestingTags
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.UIText
import org.junit.Rule
import org.junit.Test

class RecipeAddingScreenTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun shouldShowLoadingStateUi_inLoadingState(): Unit = with(rule) {
        val state = RecipeAddingScreenState.Loading

        setContent {
            RecipeAppTheme {
                RecipeAddingScreenContent(state = state)
            }
        }

        onNodeWithTag(RecipeAddingScreenLoadingStateTestingTags.RECIPE_ADDING_SCREEN_LOADING_STATE_ROOT)
            .assertExists()
    }

    @Test
    fun shouldShowContentStateUI_inContentState(): Unit = with(rule) {
        val state = RecipeAddingScreenState.ContentState(
            images = emptyList(),
            recipeName = "",
            cookingTime = UIText.PluralsResource(
                R.plurals.minutes_plural,
                0, 0
            ),

            selectedMealTypeName = UIText.DynamicText("Beer"),
            description = "",
            ingredients = emptyList(),
            isImagesError = false,
            isNameError = false,
            isCookingTimeError = false,
            isDescriptionError = false,
            isMealTypeError = false,
            isIngredientsError = false,
        )

        setContent {
            RecipeAppTheme {
                RecipeAddingScreenContent(state = state)
            }
        }

        onNodeWithTag(RecipeAddingScreenContentStateTestingTags.RECIPE_ADDING_SCREEN_CONTENT_STATE_ROOT_ELEMENT)
            .assertExists()
    }

    @Test
    fun shouldShowErrorStateUI_inErrorState(): Unit = with(rule) {
        val state =
            RecipeAddingScreenState.ErrorState(UIText.DynamicText(""), UIText.DynamicText(""))

        setContent {
            RecipeAppTheme {
                RecipeAddingScreenContent(state = state)
            }
        }
        onNodeWithTag(ErrorScreenStateTestingTags.ROOT_ELEMENT)
            .assertExists()

    }
}