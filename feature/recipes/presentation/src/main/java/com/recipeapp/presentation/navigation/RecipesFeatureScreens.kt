package com.recipeapp.presentation.navigation

sealed class RecipesFeatureScreens(val route: String) {

    object RecipesListScreen : RecipesFeatureScreens(route = "recipesListScreen")

    companion object {
        val startScreenRoute = RecipesListScreen.route
    }
}
