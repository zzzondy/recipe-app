package com.recipes.domain.states

import com.recipes.domain.models.Recipe

sealed class ObtainingRecipesResult {

    data class Success(val recipes: List<Recipe>) : ObtainingRecipesResult()

    object NetworkError : ObtainingRecipesResult()

    object OtherError : ObtainingRecipesResult()
}
