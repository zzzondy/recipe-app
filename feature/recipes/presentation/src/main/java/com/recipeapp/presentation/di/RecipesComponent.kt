package com.recipeapp.presentation.di

import com.recipeapp.presentation.screens.recipes_list.di.RecipesListScreenComponent
import dagger.Subcomponent


@RecipesComponentScope
@Subcomponent(modules = [RecipesModule::class])
interface RecipesComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): RecipesComponent
    }

    val recipesListScreenComponentFactory: RecipesListScreenComponent.Factory
}