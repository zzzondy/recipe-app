package com.recipeapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.daggerViewModel
import com.recipeapp.presentation.di.RecipesComponentProvider
import com.recipeapp.presentation.screens.recipes_list.RecipesListScreen
import com.recipeapp.presentation.screens.recipes_list.RecipesListScreenViewModel

class RecipesFeatureNavigationApi : FeatureNavigationApi {

    override val navigationRoute = RecipesFeatureScreens.navigationRoute

    @OptIn(ExperimentalAnimationApi::class)
    override fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = RecipesFeatureScreens.startScreenRoute,
            route = navigationRoute,
        ) {
            val context = navController.context.applicationContext
            val recipesComponent = (context as RecipesComponentProvider).provideRecipeComponent()

            composable(RecipesFeatureScreens.RecipesListScreen.route) {
                val recipesListScreenComponent =
                    recipesComponent.recipesListScreenComponentFactory.create()

                val viewModel: RecipesListScreenViewModel = daggerViewModel {
                    recipesListScreenComponent.recipesListScreenViewModel
                }

                RecipesListScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}