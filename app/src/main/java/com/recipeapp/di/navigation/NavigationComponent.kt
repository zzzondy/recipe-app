package com.recipeapp.di.navigation

import com.recipeapp.MainActivity
import com.recipeapp.presentation.di.RecipesFeatureNavigationModule
import com.recipeapp.presentation.navigation.RecipesFeatureNavigationApi
import dagger.Subcomponent

@NavigationScope
@Subcomponent(modules = [RecipesFeatureNavigationModule::class])
interface NavigationComponent {

    fun inject(mainActivity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): NavigationComponent
    }

    val recipesFeatureNavigationApi: RecipesFeatureNavigationApi
}
