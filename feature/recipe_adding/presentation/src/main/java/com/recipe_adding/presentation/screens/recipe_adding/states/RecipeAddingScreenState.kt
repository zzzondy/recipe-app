package com.recipe_adding.presentation.screens.recipe_adding.states

import android.graphics.Bitmap
import com.recipeapp.utils.UIText

sealed class RecipeAddingScreenState {

    object Loading : RecipeAddingScreenState()

    data class ContentState(
        val images: List<Bitmap>,
        val recipeName: String,
        val cookingTime: UIText,
        val description: String,
        val ingredients: List<IngredientItem>,
        val selectedMealTypeName: UIText,
        val isImagesError: Boolean = false,
        val isNameError: Boolean = false,
        val isCookingTimeError: Boolean = false,
        val isDescriptionError: Boolean = false,
        val isMealTypeError: Boolean = false,
        val isIngredientsError: Boolean = false,
    ) : RecipeAddingScreenState()

    data class ErrorState(val title: UIText, val subtitle: UIText) : RecipeAddingScreenState()
}