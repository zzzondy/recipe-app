package com.recipe_adding.domain.states

import com.recipe_adding.domain.models.MealType

sealed class ObtainingMealTypesResult {

    data class Success(val mealTypes: List<MealType>) : ObtainingMealTypesResult()

    object NetworkError : ObtainingMealTypesResult()

    object OtherError : ObtainingMealTypesResult()
}
