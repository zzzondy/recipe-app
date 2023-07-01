package com.recipeapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.recipeapp.presentation.navigation.RecipesFeatureScreens

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    featureNavigationApis: List<FeatureNavigationApi>,
    bottomSheetNavigator: BottomSheetNavigator,
) {
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
        AnimatedNavHost(
            navController = navController,
            startDestination = RecipesFeatureScreens.navigationRoute,
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
}