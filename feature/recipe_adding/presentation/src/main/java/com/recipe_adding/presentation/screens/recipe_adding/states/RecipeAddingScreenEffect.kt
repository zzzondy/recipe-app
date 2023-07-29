package com.recipe_adding.presentation.screens.recipe_adding.states

import com.recipeapp.utils.UIText

sealed interface RecipeAddingScreenEffect {

    object NavigateBack : RecipeAddingScreenEffect

    object OpenMealTypesChoosingDialog : RecipeAddingScreenEffect

    object ShowLoadingDialog : RecipeAddingScreenEffect

    object CloseLoadingDialog : RecipeAddingScreenEffect

    object NavigateBackOnSuccessfulResult : RecipeAddingScreenEffect

    data class ShowErrorSnackBar(val title: UIText, val subtitle: UIText) : RecipeAddingScreenEffect
}