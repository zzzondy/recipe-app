package com.recipeapp.di.navigation

import com.recipeapp.presentation.di.RecipesFeatureNavigationModule
import com.recipeapp.presentation.navigation.RecipesFeatureNavigationApi
import dagger.Subcomponent

@NavigationScope
@Subcomponent(modules = [RecipesFeatureNavigationModule::class])
interface NavigationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NavigationComponent
    }

    val recipesFeatureNavigationApi: RecipesFeatureNavigationApi
}
