package com.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    vararg featuresNavigationApi: FeatureNavigationApi,
) {
    NavHost(
        navController = navController,
        startDestination = featuresNavigationApi[0].startScreenRoute
    ) {
        featuresNavigationApi.forEach { featuresNavigationApi ->
            register(
                featureNavigationApi = featuresNavigationApi,
                navController = navController,
                modifier = modifier
            )
        }
    }

}