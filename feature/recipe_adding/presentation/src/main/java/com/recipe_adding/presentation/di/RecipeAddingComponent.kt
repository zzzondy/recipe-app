package com.recipe_adding.presentation.di

import com.recipe_adding.presentation.screens.recipe_adding.di.RecipeAddingScreenComponent
import dagger.Subcomponent

@RecipeAddingComponentScope
@Subcomponent(modules = [RecipeAddingModule::class])
interface RecipeAddingComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): RecipeAddingComponent
    }

    val recipeAddingScreenComponentFactory: RecipeAddingScreenComponent.Factory
}