package com.recipe_adding.presentation.screens.meal_type_choosing.states

import com.recipe_adding.domain.models.MealType

sealed class MealTypeChoosingBottomSheetAction {

    data class OnMealTypeClicked(val mealType: MealType) : MealTypeChoosingBottomSheetAction()

    object OnRetryButtonClicked : MealTypeChoosingBottomSheetAction()

    object OnRefreshButtonClicked : MealTypeChoosingBottomSheetAction()
}
