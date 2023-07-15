package com.recipe_adding.domain.use_cases

import com.recipe_adding.domain.repository.RecipeAddingRepository

class ObtainMealTypesByPageUseCase(private val recipeAddingRepository: RecipeAddingRepository) {

    operator fun invoke(searchQuery: String = "") =
        recipeAddingRepository.obtainMealTypesByPage(searchQuery = searchQuery)
}