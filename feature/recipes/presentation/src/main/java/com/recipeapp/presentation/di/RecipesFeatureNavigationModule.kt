package com.recipeapp.presentation.di

import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.navigation.FeatureNavigationApi
import com.recipeapp.navigation.di.NavigationScope
import com.recipeapp.presentation.navigation.RecipesFeatureBottomBarItem
import com.recipeapp.presentation.navigation.RecipesFeatureNavigationApi
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
class RecipesFeatureNavigationModule {

    @NavigationScope
    @Provides
    @IntoSet
    fun provideRecipesFeatureNavigationApi(): FeatureNavigationApi =
        RecipesFeatureNavigationApi()

    @NavigationScope
    @Provides
    @IntoSet
    fun provideRecipesFeatureBottomBarItem(): BottomBarItem = RecipesFeatureBottomBarItem()
}