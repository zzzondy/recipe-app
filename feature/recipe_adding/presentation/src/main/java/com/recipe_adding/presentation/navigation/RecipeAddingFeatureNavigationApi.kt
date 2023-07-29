package com.recipe_adding.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.recipe_adding.presentation.di.RecipeAddingComponentProvider
import com.recipe_adding.presentation.screens.meal_type_choosing.MealTypeChoosingBottomSheetScreen
import com.recipe_adding.presentation.screens.meal_type_choosing.MealTypeChoosingBottomSheetViewModel
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreen
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreenViewModel
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenAction
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.daggerViewModel

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
                    fadeOut()
                },
                popEnterTransition = {
                    fadeIn()
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                    )
                },
            ) { entry ->
                val recipeAddingScreenComponent =
                    recipeAddingComponent.recipeAddingScreenComponentFactory.create()

                val viewModel: RecipeAddingScreenViewModel = daggerViewModel {
                    recipeAddingScreenComponent.recipeAddingScreenViewModel
                }


                val mealTypeName = entry.savedStateHandle.get<String>(SELECTED_MEAL_TYPE_NAME)
                if (mealTypeName != null) {
                    viewModel.onDispatchAction(
                        RecipeAddingScreenAction.OnMealTypeSelected(
                            mealTypeName
                        )
                    )
                }

                RecipeAddingScreen(
                    navController = navController,
                    viewModel = viewModel,
                )
            }

            composable(
                route = RecipeAddingScreens.MealTypesChoosingDialog.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                    )
                }
            ) {
                val mealTypeChoosingBottomSheetComponent =
                    recipeAddingComponent.mealTypeChoosingBottomSheetComponentFactory.create()

                val viewModel: MealTypeChoosingBottomSheetViewModel = daggerViewModel {
                    mealTypeChoosingBottomSheetComponent.mealTypeChoosingBottomSheetViewModel
                }

                MealTypeChoosingBottomSheetScreen(
                    navController = navController,
                    viewModel = viewModel,
                )
            }
        }
    }

    companion object {
        const val SELECTED_MEAL_TYPE_NAME = "SELECTED_MEAL_TYPE_NAME"
    }
}