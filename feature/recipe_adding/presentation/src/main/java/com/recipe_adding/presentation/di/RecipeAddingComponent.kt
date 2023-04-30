package com.recipe_adding.presentation.di

import dagger.Subcomponent

@RecipeAddingComponentScope
@Subcomponent(modules = [RecipeAddingModule::class])
interface RecipeAddingComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): RecipeAddingComponent
    }
}