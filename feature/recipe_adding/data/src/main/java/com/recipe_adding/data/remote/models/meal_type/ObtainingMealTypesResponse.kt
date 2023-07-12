package com.recipe_adding.data.remote.models.meal_type

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ObtainingMealTypesResponse(
    @SerialName("count")
    val count: Int,

    @SerialName("next")
    val nextPage: String?,

    @SerialName("previous")
    val previousPage: String?,

    @SerialName("results")
    val results: List<RemoteMealType>,
)
