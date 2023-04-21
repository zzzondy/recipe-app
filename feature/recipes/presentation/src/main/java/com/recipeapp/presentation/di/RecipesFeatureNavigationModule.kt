package com.recipeapp.presentation.di

import com.recipeapp.presentation.navigation.RecipesFeatureNavigationApi
import dagger.Module
import dagger.Provides

@Module
class RecipesFeatureNavigationModule {

    @Provides
    fun provideRecipesFeatureNavigationApi(): RecipesFeatureNavigationApi =
        RecipesFeatureNavigationApi()
}