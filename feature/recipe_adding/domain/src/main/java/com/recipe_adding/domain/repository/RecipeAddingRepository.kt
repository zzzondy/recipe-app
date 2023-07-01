package com.recipe_adding.domain.repository

import com.recipe_adding.domain.models.Recipe
import com.recipe_adding.domain.states.UploadingRecipeResult

interface RecipeAddingRepository {

    suspend fun uploadRecipe(recipe: Recipe): UploadingRecipeResult
}