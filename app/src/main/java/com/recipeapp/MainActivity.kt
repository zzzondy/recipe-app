package com.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.theme.RecipeAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bottomBarItems: Set<@JvmSuppressWildcards BottomBarItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navigationComponent = appComponent.navigationComponentFactory.create()
        navigationComponent.inject(this)

        setContent {
            RecipeAppTheme {
                AppContent(
                    bottomBarItems = bottomBarItems.toList(),
                    featureNavigationApis = listOf(navigationComponent.recipesFeatureNavigationApi)
                )
            }
        }
    }
}