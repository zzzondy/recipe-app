package com.recipe_adding.presentation.screens.recipe_adding.di.modules

import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreenViewModel
import com.recipe_adding.presentation.screens.recipe_adding.di.RecipeAddingScreenScope
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenStateMachine
import dagger.Module
import dagger.Provides

@Module
class ScreenModule {

    @RecipeAddingScreenScope
    @Provides
    fun provideRecipeAddingScreenStateMachine(): RecipeAddingScreenStateMachine =
        RecipeAddingScreenStateMachine()


    @RecipeAddingScreenScope
    @Provides
    fun provideRecipeAddingScreenViewModel(recipeAddingScreenStateMachine: RecipeAddingScreenStateMachine): RecipeAddingScreenViewModel =
        RecipeAddingScreenViewModel(recipeAddingScreenStateMachine)
}