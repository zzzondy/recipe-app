package com.recipe_adding.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.recipe_adding.presentation.di.RecipeAddingComponentProvider
import com.recipeapp.navigation.FeatureNavigationApi

class RecipeAddingFeatureNavigationApi : FeatureNavigationApi {
    override val navigationRoute: String
        get() = RecipeAddingScreens.navigationRoute

    override fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route = navigationRoute) {
            val recipeAddingComponent =
                (LocalContext.current.applicationContext as RecipeAddingComponentProvider).provideRecipeAddingComponent()

            Column() {
                Text(text = "recipe adding screen")
            }
        }
    }
}