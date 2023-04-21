package com.recipeapp.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder


interface FeatureNavigationApi {

    val startScreenRoute: String

    fun registerFeatureNavigationGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
        modifier: Modifier = Modifier
    )
}