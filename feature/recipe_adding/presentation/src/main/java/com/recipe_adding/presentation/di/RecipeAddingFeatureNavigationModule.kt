package com.recipe_adding.presentation.di

import com.recipe_adding.presentation.navigation.RecipeAddingFeatureNavigationApi
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.di.NavigationScope
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class RecipeAddingFeatureNavigationModule {

    @NavigationScope
    @IntoSet
    @Provides
    fun provideRecipeAddingFeatureNavigationApi(): FeatureNavigationApi =
        RecipeAddingFeatureNavigationApi()
}