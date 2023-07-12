package com.recipe_adding.presentation.screens.meal_type_choosing.states.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.recipe_adding.presentation.screens.meal_type_choosing.ui_components.MealTypeItem
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun MealTypeChoosingLoadingState(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        userScrollEnabled = false,
    ) {
        items(MealTypeChoosingLoadingStateConstants.ITEMS_COUNT, key = { it }) {
            MealTypeItem(
                mealType = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = RecipeAppTheme.paddings.medium)
            )
        }
    }
}

private object MealTypeChoosingLoadingStateConstants {

    const val ITEMS_COUNT = 20
}