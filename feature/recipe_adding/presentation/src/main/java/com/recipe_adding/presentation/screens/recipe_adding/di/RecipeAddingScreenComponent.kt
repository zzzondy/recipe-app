package com.recipe_adding.presentation.screens.recipe_adding.di

import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreenViewModel
import com.recipe_adding.presentation.screens.recipe_adding.di.modules.ScreenModule
import dagger.Subcomponent


@RecipeAddingScreenScope
@Subcomponent(modules = [ScreenModule::class])
interface RecipeAddingScreenComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): RecipeAddingScreenComponent
    }

    val recipeAddingScreenViewModel: RecipeAddingScreenViewModel
}