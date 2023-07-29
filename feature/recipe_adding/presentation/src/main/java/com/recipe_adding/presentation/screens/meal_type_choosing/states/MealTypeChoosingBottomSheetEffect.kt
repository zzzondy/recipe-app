package com.recipe_adding.presentation.screens.meal_type_choosing.states

sealed class MealTypeChoosingBottomSheetEffect {

    data class OnMealTypeSelected(val mealTypeName: String) : MealTypeChoosingBottomSheetEffect()

    object RetryObtainingData : MealTypeChoosingBottomSheetEffect()

    object RefreshData : MealTypeChoosingBottomSheetEffect()

    object CloseScreen : MealTypeChoosingBottomSheetEffect()
}