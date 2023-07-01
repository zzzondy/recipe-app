package com.recipe_adding.domain.models

data class Recipe(
    val mealType: String,
    val ingredients: List<Ingredient>,
    val cookingTime: Int,
    val description: String,
    val name: String,
    val images: List<String>,
)
