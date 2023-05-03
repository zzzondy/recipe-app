package com.recipe_adding.presentation.navigation

sealed class RecipeAddingScreens(val route: String) {

    object RecipeAddingScreen : RecipeAddingScreens(route = "recipe_adding_screen")

    companion object {
        const val navigationRoute = "recipe_adding_screen"
    }
}
