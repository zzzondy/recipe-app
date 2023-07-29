package com.recipe_adding.domain.use_cases

import com.recipe_adding.domain.models.Recipe
import com.recipe_adding.domain.repository.RecipeAddingRepository

class UploadRecipeUseCase(private val recipeAddingRepository: RecipeAddingRepository) {

    suspend operator fun invoke(recipe: Recipe) = recipeAddingRepository.uploadRecipe(recipe)
}