package com.recipeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.recipe_adding.presentation.navigation.RecipeAddingScreens
import com.recipeapp.components.buttons.DefaultIconButton
import com.recipeapp.navigation.AppNavGraph
import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.ui.BottomNavBar
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun AppContent(
    bottomBarItems: List<BottomBarItem>,
    featureNavigationApis: List<FeatureNavigationApi>
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                bottomBarItems = bottomBarItems,
                middleButton = {
                    DefaultIconButton(onClick = { navController.navigate(RecipeAddingScreens.navigationRoute) }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            )
        },
        backgroundColor = RecipeAppTheme.colors.white0,
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { paddingValues ->
        AppNavGraph(
            navController = navController,
            featureNavigationApis = featureNavigationApis,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}