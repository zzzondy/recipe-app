package com.recipeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.recipeapp.components.buttons.CircleButton
import com.recipeapp.navigation.AppNavGraph
import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.ui.BottomNavBar

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
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            CircleButton {

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        AppNavGraph(
            navController = navController,
            featureNavigationApis = featureNavigationApis,
            modifier = Modifier.padding(paddingValues),
        )
    }
}