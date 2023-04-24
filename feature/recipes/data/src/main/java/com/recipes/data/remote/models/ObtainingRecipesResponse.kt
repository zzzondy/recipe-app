package com.recipes.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ObtainingRecipesResponse(
    val recipes: List<RemoteRecipe>
)
