package com.recipeapp.presentation.di

import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.presentation.navigation.RecipesFeatureBottomBarItem
import com.recipeapp.presentation.navigation.RecipesFeatureNavigationApi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class RecipesFeatureNavigationModule {

    @Provides
    fun provideRecipesFeatureNavigationApi(): RecipesFeatureNavigationApi =
        RecipesFeatureNavigationApi()

    @Provides
    @IntoSet
    fun provideRecipesFeatureBottomBarItem(): BottomBarItem = RecipesFeatureBottomBarItem()
}