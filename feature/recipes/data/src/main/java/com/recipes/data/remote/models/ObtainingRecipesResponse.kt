package com.recipes.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ObtainingRecipesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<RemoteRecipe>
)
