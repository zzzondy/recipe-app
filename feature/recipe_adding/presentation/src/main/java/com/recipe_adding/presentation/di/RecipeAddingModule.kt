package com.recipe_adding.presentation.di

import com.recipe_adding.presentation.screens.recipe_adding.di.RecipeAddingScreenComponent
import com.recipe_adding.presentation.screens.recipe_adding.di.modules.NetworkServiceModule
import com.recipe_adding.presentation.screens.recipe_adding.di.modules.RemoteBindingModule
import com.recipe_adding.presentation.screens.recipe_adding.di.modules.RepositoryBindingModule
import dagger.Module

@Module(
    subcomponents = [RecipeAddingScreenComponent::class],
    includes = [NetworkServiceModule::class, RemoteBindingModule::class, RepositoryBindingModule::class]
)
class RecipeAddingModule