package com.recipe_adding.presentation.screens.meal_type_choosing.di.modules

import com.recipe_adding.domain.repository.RecipeAddingRepository
import com.recipe_adding.domain.use_cases.ObtainMealTypesByPageUseCase
import com.recipe_adding.presentation.screens.meal_type_choosing.di.MealTypeChoosingBottomSheetScope
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @MealTypeChoosingBottomSheetScope
    @Provides
    fun provideObtainMealTypesByPageUseCase(recipeAddingRepository: RecipeAddingRepository): ObtainMealTypesByPageUseCase =
        ObtainMealTypesByPageUseCase(recipeAddingRepository)
}