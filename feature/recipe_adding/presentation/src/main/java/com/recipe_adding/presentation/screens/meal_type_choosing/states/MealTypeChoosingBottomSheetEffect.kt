package com.recipe_adding.presentation.screens.meal_type_choosing.states

import com.recipe_adding.domain.models.MealType

sealed class MealTypeChoosingBottomSheetEffect {

    data class OnMealTypeSelected(val mealType: MealType) : MealTypeChoosingBottomSheetEffect()

    object RetryObtainingData : MealTypeChoosingBottomSheetEffect()

    object RefreshData : MealTypeChoosingBottomSheetEffect()
}