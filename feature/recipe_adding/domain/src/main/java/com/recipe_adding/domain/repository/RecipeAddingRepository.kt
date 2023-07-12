package com.recipe_adding.domain.repository

import androidx.paging.PagingData
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.domain.models.Recipe
import com.recipe_adding.domain.states.UploadingRecipeResult
import kotlinx.coroutines.flow.Flow

interface RecipeAddingRepository {

    suspend fun uploadRecipe(recipe: Recipe): UploadingRecipeResult

    fun obtainMealTypesByPage(): Flow<PagingData<MealType>>
}