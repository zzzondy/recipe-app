package com.recipes.domain.repository

import androidx.paging.PagingData
import com.recipes.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    fun obtainRecipesWithPaging(): Flow<PagingData<Recipe>>
}