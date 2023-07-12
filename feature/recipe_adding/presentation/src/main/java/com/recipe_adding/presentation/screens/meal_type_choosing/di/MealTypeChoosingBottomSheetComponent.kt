package com.recipe_adding.presentation.screens.meal_type_choosing.di

import com.recipe_adding.presentation.screens.meal_type_choosing.MealTypeChoosingBottomSheetViewModel
import com.recipe_adding.presentation.screens.meal_type_choosing.di.modules.ScreenModule
import dagger.Subcomponent


@MealTypeChoosingBottomSheetScope
@Subcomponent(modules = [ScreenModule::class])
interface MealTypeChoosingBottomSheetComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): MealTypeChoosingBottomSheetComponent
    }

    val mealTypeChoosingBottomSheetViewModel: MealTypeChoosingBottomSheetViewModel
}