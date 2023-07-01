package com.recipe_adding.domain.models

data class MealType(
    val id: Long = -1,
    val name: String,
    val isEditable: Boolean = false,
)
