package com.recipe_adding.presentation.screens.recipe_adding.states.ui

import MealTypeSection
import android.graphics.Bitmap
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.recipe_adding.presentation.R
import com.recipe_adding.presentation.screens.recipe_adding.states.IngredientItem
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.CookingTimeSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.DescriptionSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.ImagesSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.IngredientsSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.NameSection
import com.recipeapp.components.buttons.DefaultButton
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.UIText

@Composable
fun RecipeAddingScreenContentState(
    images: List<Bitmap>,
    recipeName: String,
    cookingTime: String,
    description: String,
    ingredients: List<IngredientItem>,
    selectedMealTypeName: UIText,
    onChangedIngredient: (Int, String) -> Unit,
    onChangedIngredientQuantity: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onAddImageClicked: () -> Unit = {},
    onRemoveImageClicked: (Int) -> Unit = {},
    onReplaceImageClicked: (Int) -> Unit = {},
    onChangedName: (String) -> Unit = {},
    onCookingTimeClicked: () -> Unit = {},
    onAddNewIngredient: () -> Unit = {},
    onRemoveIngredient: (Int) -> Unit = {},
    onSaveRecipe: () -> Unit = {},
    onDescriptionChanged: (String) -> Unit = {},
    onMealTypeSectionClicked: () -> Unit = {},
    isImagesError: Boolean = false,
    isNameError: Boolean = false,
    isCookingTimeError: Boolean = false,
    isMealTypeError: Boolean = false,
    isDescriptionError: Boolean = false,
    isIngredientsError: Boolean = false,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .testTag(RecipeAddingScreenContentStateTestingTags.RECIPE_ADDING_SCREEN_CONTENT_STATE_ROOT_ELEMENT)
            .imePadding()
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            }
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .testTag(RecipeAddingScreenContentStateTestingTags.LAZY_COLUMN_TAG)
                .weight(1f)
        ) {
            item(key = RecipeAddingScreenContentStateSections.IMAGES_SECTION) {
                ImagesSection(
                    images = images,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(RecipeAppTheme.sizes.imageHeight),
                    onAddImageClicked = onAddImageClicked,
                    onRemoveImageClicked = onRemoveImageClicked,
                    onReplaceImageClicked = onReplaceImageClicked,
                    isError = isImagesError,
                )
            }

            item(key = RecipeAddingScreenContentStateSections.RECIPE_NAME_SECTION) {
                NameSection(
                    name = recipeName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(RecipeAppTheme.paddings.medium),
                    onChangedName = onChangedName,
                    isError = isNameError,
                )
            }

            item(key = RecipeAddingScreenContentStateSections.COOKING_TIME_SECTION) {
                CookingTimeSection(
                    time = cookingTime,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(RecipeAppTheme.paddings.medium),
                    onClick = onCookingTimeClicked,
                    isError = isCookingTimeError,
                )
            }

            item(key = RecipeAddingScreenContentStateSections.MEAL_TYPES_SECTION) {
                MealTypeSection(
                    selectedMealTypeName = selectedMealTypeName,
                    onClick = onMealTypeSectionClicked,
                    isError = isMealTypeError,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(RecipeAppTheme.paddings.medium),
                    )
            }

            item(key = RecipeAddingScreenContentStateSections.DESCRIPTION_SECTION) {
                DescriptionSection(
                    description = description,
                    onDescriptionChanged = onDescriptionChanged,
                    modifier = Modifier
                        .padding(RecipeAppTheme.paddings.medium)
                        .fillMaxWidth(),
                    isError = isDescriptionError
                )
            }

            item(key = RecipeAddingScreenContentStateSections.INGREDIENTS_SECTION) {
                IngredientsSection(
                    ingredients = ingredients,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(RecipeAppTheme.paddings.medium),
                    onAddNewIngredient = onAddNewIngredient,
                    onRemoveIngredient = onRemoveIngredient,
                    onChangedIngredient = onChangedIngredient,
                    onChangedIngredientQuantity = onChangedIngredientQuantity,
                    isError = isIngredientsError,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            contentAlignment = Alignment.Center
        ) {
            DefaultButton(
                modifier = Modifier
                    .padding(
                        horizontal = RecipeAppTheme.paddings.medium,
                        vertical = RecipeAppTheme.paddings.small
                    )
                    .fillMaxSize(),
                onClick = {
                    focusManager.clearFocus()
                    onSaveRecipe()
                }
            ) {
                Text(
                    text = stringResource(R.string.save_recipe),
                    style = RecipeAppTheme.typography.boldP
                )
            }
        }
    }
}

object RecipeAddingScreenContentStateSections {

    const val IMAGES_SECTION = "IMAGES_SECTION"
    const val RECIPE_NAME_SECTION = "RECIPE_NAME_SECTION"
    const val COOKING_TIME_SECTION = "COOKING_TIME_SECTION"
    const val MEAL_TYPES_SECTION = "MEAL_TYPES_SECTION"
    const val DESCRIPTION_SECTION = "DESCRIPTION_SECTION"
    const val INGREDIENTS_SECTION = "INGREDIENTS_SECTION"
}

object RecipeAddingScreenContentStateTestingTags {

    const val RECIPE_ADDING_SCREEN_CONTENT_STATE_ROOT_ELEMENT =
        "RECIPE_ADDING_SCREEN_CONTENT_STATE_ROOT_ELEMENT"
    const val LAZY_COLUMN_TAG = "LAZY_COLUMN_TAG"
}