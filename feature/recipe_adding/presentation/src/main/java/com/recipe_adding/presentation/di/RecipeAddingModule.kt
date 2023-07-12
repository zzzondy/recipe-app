package com.recipe_adding.presentation.di

import com.recipe_adding.presentation.screens.recipe_adding.di.RecipeAddingScreenComponent
import com.recipe_adding.presentation.di.modules.NetworkServiceModule
import com.recipe_adding.presentation.di.modules.RemoteBindingModule
import com.recipe_adding.presentation.di.modules.RepositoryBindingModule
import com.recipe_adding.presentation.screens.meal_type_choosing.di.MealTypeChoosingBottomSheetComponent
import dagger.Module

@Module(
    subcomponents = [RecipeAddingScreenComponent::class, MealTypeChoosingBottomSheetComponent::class],
    includes = [NetworkServiceModule::class, RemoteBindingModule::class, RepositoryBindingModule::class]
)
class RecipeAddingModule