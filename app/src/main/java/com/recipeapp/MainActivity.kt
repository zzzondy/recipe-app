package com.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.recipeapp.navigation.AppNavGraph
import com.recipeapp.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navigationComponent = appComponent.navigationComponentFactory.create()

        setContent {
            val navController = rememberNavController()
            RecipeAppTheme {
                AppNavGraph(
                    navController = navController,
                    modifier = Modifier,
                    navigationComponent.recipesFeatureNavigationApi,
                )
            }
        }
    }
}