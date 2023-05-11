package com.recipe_adding.presentation.screens.recipe_adding.di.modules

import com.recipe_adding.domain.use_cases.ValidateQuantityUseCase
import com.recipe_adding.presentation.screens.recipe_adding.di.RecipeAddingScreenScope
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @RecipeAddingScreenScope
    @Provides
    fun provideValidateQuantityUseCase(): ValidateQuantityUseCase = ValidateQuantityUseCase()
}