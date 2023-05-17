package com.recipeapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.recipeapp.presentation.navigation.RecipesFeatureScreens

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    featureNavigationApis: List<FeatureNavigationApi>,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = RecipesFeatureScreens.navigationRoute
    ) {
        featureNavigationApis.forEach { featuresNavigationApi ->
            register(
                featureNavigationApi = featuresNavigationApi,
                navController = navController,
                modifier = modifier
            )
        }
    }
}