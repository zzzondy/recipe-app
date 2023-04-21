package com.recipeapp.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.presentation.di.RecipesComponentProvider
import com.recipeapp.presentation.screens.recipes_list_screen.RecipesListScreen

class RecipesFeatureNavigationApi : FeatureNavigationApi {

    override val startScreenRoute = RecipesFeatureScreens.startScreenRoute

    override fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(startScreenRoute) {
            val context = LocalContext.current
            val recipesComponent = (context.applicationContext as RecipesComponentProvider).provideRecipeComponent()
            RecipesListScreen()
        }
    }
}