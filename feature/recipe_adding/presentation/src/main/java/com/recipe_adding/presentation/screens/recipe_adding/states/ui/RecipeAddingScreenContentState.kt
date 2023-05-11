package com.recipe_adding.presentation.screens.recipe_adding.states.ui

import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.presentation.R
import com.recipe_adding.presentation.screens.recipe_adding.states.IngredientItem
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.CookingTimeSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.DescriptionSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.ImageSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.IngredientsSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.MealTypeSection
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components.NameSection
import com.recipeapp.components.buttons.DefaultButton
import com.recipeapp.theme.RecipeAppTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeAddingScreenContentState(
    images: List<Uri>,
    recipeName: String,
    cookingTime: String,
    mealTypes: List<MealType>,
    description: String,
    ingredients: List<IngredientItem>,
    onChangedIngredient: (Int, String) -> Unit,
    onChangedIngredientQuantity: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
    selectedMealType: MealType = mealTypes[0],
    customMealType: String = "",
    onAddImageClicked: () -> Unit = {},
    onRemoveImageClicked: (Uri) -> Unit = {},
    onReplaceImageClicked: (Uri) -> Unit = {},
    onChangedName: (String) -> Unit = {},
    onCookingTimeClicked: () -> Unit = {},
    onAddNewIngredient: () -> Unit = {},
    onRemoveIngredient: (Int) -> Unit = {},
    onSaveRecipe: () -> Unit = {},
    onDescriptionChanged: (String) -> Unit = {},
    onMealTypeChanged: (MealType) -> Unit = {},
    onChangedCustomMealType: (String) -> Unit = {},
    isImagesError: Boolean = false,
    isNameError: Boolean = false,
    isCookingTimeError: Boolean = false,
    isDescriptionError: Boolean = false,
    isMealTypeError: Boolean = false,
    isIngredientsError: Boolean = false,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .imePadding()
            .imeNestedScroll()
            .animateContentSize()
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            }
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .animateContentSize()
        ) {
            item(key = "image_section") {
                ImageSection(
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

            item(key = "name_section") {
                NameSection(
                    name = recipeName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(RecipeAppTheme.paddings.medium),
                    onChangedName = onChangedName,
                    isError = isNameError,
                )
            }

            item(key = "cooking_time_section") {
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

            item(key = "meal_types_section") {
                MealTypeSection(
                    mealTypes = mealTypes,
                    selectedMealType = selectedMealType,
                    customMealType = customMealType,
                    isError = isMealTypeError,
                    onMealTypeChanged = onMealTypeChanged,
                    onChangedCustomMealType = onChangedCustomMealType,
                    modifier = Modifier
                        .padding(RecipeAppTheme.paddings.medium)
                        .fillMaxWidth()
                )
            }

            item(key = "description_section") {
                DescriptionSection(
                    description = description,
                    onDescriptionChanged = onDescriptionChanged,
                    modifier = Modifier
                        .padding(RecipeAppTheme.paddings.medium)
                        .fillMaxWidth(),
                    isError = isDescriptionError
                )
            }

            item(key = "ingredients_section") {
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