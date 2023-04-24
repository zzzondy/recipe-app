package com.recipeapp.presentation.screens.recipes_list.di.modules

import com.recipeapp.presentation.screens.recipes_list.RecipesListScreenViewModel
import com.recipeapp.presentation.screens.recipes_list.di.RecipesListScreenScope
import com.recipes.domain.use_cases.ObtainRecipesUseCase
import dagger.Module
import dagger.Provides

@Module
class ScreenModule {

    @RecipesListScreenScope
    @Provides
    fun provideRecipesListScreenViewModel(obtainRecipesUseCase: ObtainRecipesUseCase): RecipesListScreenViewModel =
        RecipesListScreenViewModel(obtainRecipesUseCase)
}