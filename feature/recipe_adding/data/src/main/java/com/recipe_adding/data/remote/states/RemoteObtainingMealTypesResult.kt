package com.recipe_adding.data.remote.states

import com.recipe_adding.data.remote.models.meal_type.ObtainingMealTypesResponse

sealed class RemoteObtainingMealTypesResult {

    data class Success(val response: ObtainingMealTypesResponse) : RemoteObtainingMealTypesResult()

    object NetworkError : RemoteObtainingMealTypesResult()

    object OtherError : RemoteObtainingMealTypesResult()
}
