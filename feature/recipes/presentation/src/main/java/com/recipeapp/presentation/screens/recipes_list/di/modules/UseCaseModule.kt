package com.recipeapp.presentation.screens.recipes_list.di.modules

import com.recipeapp.presentation.screens.recipes_list.di.RecipesListScreenScope
import com.recipes.domain.repository.RecipesRepository
import com.recipes.domain.use_cases.ObtainRecipesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @RecipesListScreenScope
    @Provides
    fun provideObtainRecipesUseCase(recipesRepository: RecipesRepository): ObtainRecipesUseCase =
        ObtainRecipesUseCase(recipesRepository)
}