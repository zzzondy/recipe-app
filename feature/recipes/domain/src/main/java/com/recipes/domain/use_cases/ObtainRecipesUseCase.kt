package com.recipes.domain.use_cases

import androidx.paging.PagingData
import com.recipes.domain.models.Recipe
import com.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class ObtainRecipesUseCase(private val recipesRepository: RecipesRepository) {

    operator fun invoke(): Flow<PagingData<Recipe>> =
        recipesRepository.obtainRecipesWithPaging()
}