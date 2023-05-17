package com.recipe_adding.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.recipe_adding.presentation.di.RecipeAddingComponentProvider
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreen
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreenViewModel
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenEffect
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.daggerViewModel
import com.recipeapp.utils.collectAsEffect

class RecipeAddingFeatureNavigationApi : FeatureNavigationApi {
    override val navigationRoute: String
        get() = RecipeAddingScreens.navigationRoute

    @OptIn(ExperimentalAnimationApi::class)
    override fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier
    ) {
        val recipeAddingComponent =
            (navController.context.applicationContext as RecipeAddingComponentProvider).provideRecipeAddingComponent()

        navGraphBuilder.navigation(
            route = navigationRoute,
            startDestination = RecipeAddingScreens.startDestination,
        ) {
            composable(
                route = RecipeAddingScreens.RecipeAddingScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                    )
                },
            ) {
                val recipeAddingScreenComponent =
                    recipeAddingComponent.recipeAddingScreenComponentFactory.create()

                val viewModel: RecipeAddingScreenViewModel = daggerViewModel {
                    recipeAddingScreenComponent.recipeAddingScreenViewModel
                }

                viewModel.effect.collectAsEffect { effect ->
                    when (effect) {
                        RecipeAddingScreenEffect.NavigateBack -> navController.popBackStack()
                    }
                }

                RecipeAddingScreen(
                    state = viewModel.state.collectAsState().value,
                    onDispatchAction = viewModel::dispatchAction
                )
            }
        }
    }
}