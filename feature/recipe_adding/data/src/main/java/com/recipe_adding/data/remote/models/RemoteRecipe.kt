package com.recipe_adding.data.remote.models

import com.recipe_adding.data.remote.models.meal_type.RemoteMealType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRecipe(

    @SerialName("id")
    val id: Long = -1,

    @SerialName("food_type")
    val mealType: RemoteMealType,

    @SerialName("ingredients")
    val ingredients: List<RemoteIngredient>,

    @SerialName("cooking_time")
    val cookingTime: Int,

    @SerialName("title")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("images")
    val images: List<RemoteRecipeImage>
)
