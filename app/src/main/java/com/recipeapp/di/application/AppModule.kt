package com.recipeapp.di.application

import com.recipe_adding.presentation.di.RecipeAddingComponent
import com.recipeapp.di.navigation.NavigationComponent
import com.recipeapp.network.di.NetworkModule
import com.recipeapp.presentation.di.RecipesComponent
import dagger.Module

@Module(
    subcomponents = [NavigationComponent::class, RecipesComponent::class, RecipeAddingComponent::class],
    includes = [NetworkModule::class]
)
class AppModule