package com.recipe_adding.presentation.screens.recipe_adding.states

data class IngredientItem(
    val name: String,
    val quantity: String,
    val isNameError: Boolean = false,
    val isQuantityError: Boolean = false,
)
