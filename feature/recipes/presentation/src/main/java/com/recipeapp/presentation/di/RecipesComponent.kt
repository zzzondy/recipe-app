package com.recipeapp.presentation.di

import dagger.Subcomponent


@RecipesComponentScope
@Subcomponent
interface RecipesComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): RecipesComponent
    }
}