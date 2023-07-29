package com.recipe_adding.presentation.screens.meal_type_choosing.di.modules

import com.recipe_adding.domain.use_cases.ObtainMealTypesByPageUseCase
import com.recipe_adding.presentation.screens.meal_type_choosing.MealTypeChoosingBottomSheetViewModel
import com.recipe_adding.presentation.screens.meal_type_choosing.di.MealTypeChoosingBottomSheetScope
import dagger.Module
import dagger.Provides

@Module(includes = [UseCaseModule::class])
class ScreenModule {

    @MealTypeChoosingBottomSheetScope
    @Provides
    fun provideMealTypeChoosingBottomSheetViewModel(obtainMealTypesByPageUseCase: ObtainMealTypesByPageUseCase): MealTypeChoosingBottomSheetViewModel =
        MealTypeChoosingBottomSheetViewModel(obtainMealTypesByPageUseCase)
}