package com.recipe_adding.presentation.navigation

sealed class RecipeAddingScreens(val route: String) {

    object RecipeAddingScreen : RecipeAddingScreens(route = "recipe_adding_screen")

    object MealTypesChoosingDialog : RecipeAddingScreens(route = "meal_types_choosing_dialog")

    companion object {
        const val navigationRoute = "recipe_adding_navigation"
        val startDestination = RecipeAddingScreen.route
    }
}
