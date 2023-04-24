package com.recipes.domain.use_cases

import com.recipes.domain.repository.RecipesRepository
import com.recipes.domain.states.ObtainingRecipesResult

class ObtainRecipesUseCase(private val recipesRepository: RecipesRepository) {

    suspend operator fun invoke(): ObtainingRecipesResult = recipesRepository.obtainRecipes()
}