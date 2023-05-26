package com.recipe_adding.presentation.screens.recipe_adding.states

import android.net.Uri
import com.recipe_adding.domain.models.MealType
import com.recipeapp.utils.UIText

sealed class RecipeAddingScreenState {

    object Loading : RecipeAddingScreenState()

    data class ContentState(
        val images: List<Uri>,
        val recipeName: String,
        val cookingTime: UIText,
        val mealTypes: List<MealType>,
        val description: String,
        val ingredients: List<IngredientItem>,
        val selectedMealType: MealType = mealTypes[0],
        val customMealType: String = "",
        val isImagesError: Boolean = false,
        val isNameError: Boolean = false,
        val isCookingTimeError: Boolean = false,
        val isDescriptionError: Boolean = false,
        val isMealTypeError: Boolean = false,
        val isIngredientsError: Boolean = false,
    ) : RecipeAddingScreenState()

    data class ErrorState(val title: UIText, val subtitle: UIText) : RecipeAddingScreenState()
}