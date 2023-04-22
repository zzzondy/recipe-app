package com.recipeapp.presentation.navigation

sealed class RecipesFeatureScreens(val route: String) {

    object RecipesListScreen : RecipesFeatureScreens(route = "recipes_list_screen")

    companion object {
        const val navigationRoute = "recipes_feature_navigation"
        val startScreenRoute = RecipesListScreen.route
    }
}
