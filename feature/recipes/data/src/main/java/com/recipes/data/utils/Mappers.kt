package com.recipes.data.utils

import com.recipes.data.remote.models.RemoteRecipe
import com.recipes.data.remote.states.RemoteObtainingRecipesResult
import com.recipes.domain.models.Recipe
import com.recipes.domain.states.ObtainingRecipesResult


fun RemoteRecipe.toDomain(): Recipe = Recipe(title, description)

fun RemoteObtainingRecipesResult.toDomain(): ObtainingRecipesResult =
    when (this) {
        is RemoteObtainingRecipesResult.Success -> ObtainingRecipesResult.Success(this.recipes.map { it.toDomain() })

        is RemoteObtainingRecipesResult.NetworkError -> ObtainingRecipesResult.NetworkError

        is RemoteObtainingRecipesResult.OtherError -> ObtainingRecipesResult.OtherError
    }