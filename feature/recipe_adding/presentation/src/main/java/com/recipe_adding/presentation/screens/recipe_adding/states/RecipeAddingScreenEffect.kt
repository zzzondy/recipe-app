package com.recipe_adding.presentation.screens.recipe_adding.states

sealed interface RecipeAddingScreenEffect {

    object NavigateBack : RecipeAddingScreenEffect

    object OpenMealTypesChoosingDialog : RecipeAddingScreenEffect

    object ShowLoadingDialog : RecipeAddingScreenEffect

    object CloseLoadingDialog : RecipeAddingScreenEffect

    object NavigateBackOnSuccessfulResult : RecipeAddingScreenEffect
}