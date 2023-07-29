package com.recipe_adding.presentation.screens.recipe_adding.di.modules

import com.recipe_adding.domain.use_cases.UploadRecipeUseCase
import com.recipe_adding.domain.use_cases.ValidateQuantityUseCase
import com.recipe_adding.presentation.screens.recipe_adding.RecipeAddingScreenViewModel
import com.recipe_adding.presentation.screens.recipe_adding.di.RecipeAddingScreenScope
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenStateMachine
import dagger.Module
import dagger.Provides

@Module(includes = [UseCaseModule::class])
class ScreenModule {

    @RecipeAddingScreenScope
    @Provides
    fun provideRecipeAddingScreenStateMachine(
        validateQuantityUseCase: ValidateQuantityUseCase,
        uploadRecipeUseCase: UploadRecipeUseCase
    ): RecipeAddingScreenStateMachine =
        RecipeAddingScreenStateMachine(validateQuantityUseCase, uploadRecipeUseCase)


    @RecipeAddingScreenScope
    @Provides
    fun provideRecipeAddingScreenViewModel(recipeAddingScreenStateMachine: RecipeAddingScreenStateMachine): RecipeAddingScreenViewModel =
        RecipeAddingScreenViewModel(recipeAddingScreenStateMachine)
}