package com.recipe_adding.presentation.screens.meal_type_choosing.states

sealed class MealTypeChoosingBottomSheetAction {

    data class OnMealTypeClicked(val mealTypeName: String) : MealTypeChoosingBottomSheetAction()

    object OnRetryButtonClicked : MealTypeChoosingBottomSheetAction()

    object OnRefreshButtonClicked : MealTypeChoosingBottomSheetAction()

    data class OnTypingSearchQuery(val query: String) : MealTypeChoosingBottomSheetAction()

    data class OnCreateNewMealTypeClicked(val mealTypeName: String) :
        MealTypeChoosingBottomSheetAction()

    object OnCloseButtonClicked : MealTypeChoosingBottomSheetAction()
}
