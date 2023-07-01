package com.recipe_adding.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteIngredient(

    @SerialName("ingredient_name")
    val ingredientName: RemoteIngredientName,

    @SerialName("quantity")
    val quantity: String,
)


@Serializable
data class RemoteIngredientName(

    @SerialName("id")
    val id: Long = -1,

    @SerialName("title")
    val name: String,
)
