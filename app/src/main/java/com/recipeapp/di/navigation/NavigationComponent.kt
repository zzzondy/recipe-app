package com.recipeapp.di.navigation

import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.di.NavigationScope
import com.recipeapp.presentation.di.RecipesFeatureNavigationModule
import dagger.Subcomponent

@NavigationScope
@Subcomponent(modules = [RecipesFeatureNavigationModule::class])
interface NavigationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NavigationComponent
    }

    val bottomBarItems: Set<@JvmSuppressWildcards BottomBarItem>

    val featureNavigationApis: Set<@JvmSuppressWildcards FeatureNavigationApi>
}
