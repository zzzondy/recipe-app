package com.recipeapp.di.application

import com.recipeapp.di.navigation.NavigationComponent
import com.recipeapp.presentation.di.RecipesComponent
import dagger.Module

@Module(subcomponents = [NavigationComponent::class, RecipesComponent::class])
class AppModule