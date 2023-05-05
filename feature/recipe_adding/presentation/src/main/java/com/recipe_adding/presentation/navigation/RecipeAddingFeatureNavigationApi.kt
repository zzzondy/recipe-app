package com.recipe_adding.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.recipe_adding.presentation.di.RecipeAddingComponentProvider
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreen
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreenViewModel
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.daggerViewModel

class RecipeAddingFeatureNavigationApi : FeatureNavigationApi {
    override val navigationRoute: String
        get() = RecipeAddingScreens.navigationRoute

    override fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        val recipeAddingComponent =
            (navController.context.applicationContext as RecipeAddingComponentProvider).provideRecipeAddingComponent()

        navGraphBuilder.navigation(
            route = navigationRoute,
            startDestination = RecipeAddingScreens.startDestination
        ) {
            composable(route = RecipeAddingScreens.RecipeAddingScreen.route) {
                val recipeAddingScreenComponent =
                    recipeAddingComponent.recipeAddingScreenComponentFactory.create()

                val viewModel: RecipeAddingScreenViewModel = daggerViewModel {
                    recipeAddingScreenComponent.recipeAddingScreenViewModel
                }

                RecipeAddingScreen(
                    state = viewModel.state.collectAsState().value,
                    onDispatchAction = viewModel::dispatchAction
                )
            }
        }
    }
}