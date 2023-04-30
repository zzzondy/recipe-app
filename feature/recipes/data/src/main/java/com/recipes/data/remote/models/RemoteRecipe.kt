package com.recipes.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRecipe(
    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("food_type")
    val foodType: Int
)
