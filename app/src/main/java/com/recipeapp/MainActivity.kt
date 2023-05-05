package com.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.recipeapp.theme.RecipeAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val navigationComponent = appComponent.navigationComponentFactory.create()

        setContent {
            RecipeAppTheme {
                AppContent(
                    bottomBarItems = navigationComponent.bottomBarItems.toList(),
                    featureNavigationApis = navigationComponent.featureNavigationApis.toList()
                )
            }
        }
    }
}