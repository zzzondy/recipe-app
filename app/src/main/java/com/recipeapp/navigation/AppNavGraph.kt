package com.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.recipeapp.presentation.navigation.RecipesFeatureScreens

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    featureNavigationApis: List<FeatureNavigationApi>,
) {
    NavHost(
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