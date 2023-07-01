package com.recipe_adding.presentation.screens.recipe_adding.states

sealed interface RecipeAddingScreenEffect {

    object NavigateBack : RecipeAddingScreenEffect

    object OpenMealTypesChoosingDialog : RecipeAddingScreenEffect
}