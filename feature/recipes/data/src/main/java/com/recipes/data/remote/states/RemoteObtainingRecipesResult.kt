package com.recipes.data.remote.states

import com.recipes.data.remote.models.RemoteRecipe

sealed class RemoteObtainingRecipesResult {

    data class Success(val recipes: List<RemoteRecipe>) : RemoteObtainingRecipesResult()

    object NetworkError : RemoteObtainingRecipesResult()

    object OtherError : RemoteObtainingRecipesResult()
}
