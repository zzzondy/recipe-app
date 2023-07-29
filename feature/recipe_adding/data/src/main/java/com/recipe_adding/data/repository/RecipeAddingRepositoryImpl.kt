package com.recipe_adding.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.recipe_adding.data.paging.MealTypesPagingSource
import com.recipe_adding.data.remote.repository.RemoteRecipeAddingRepository
import com.recipe_adding.data.util.toDomain
import com.recipe_adding.data.util.toRemote
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.domain.models.Recipe
import com.recipe_adding.domain.repository.RecipeAddingRepository
import com.recipe_adding.domain.states.UploadingRecipeResult
import kotlinx.coroutines.flow.Flow

class RecipeAddingRepositoryImpl(private val remoteRecipeAddingRepository: RemoteRecipeAddingRepository) :
    RecipeAddingRepository {

    override suspend fun uploadRecipe(recipe: Recipe): UploadingRecipeResult =
        remoteRecipeAddingRepository.uploadRecipe(recipe.toRemote()).toDomain()

    override fun obtainMealTypesByPage(searchQuery: String): Flow<PagingData<MealType>> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PAGE_SIZE),
            pagingSourceFactory = {
                MealTypesPagingSource(
                    searchQuery,
                    remoteRecipeAddingRepository
                )
            }
        ).flow

    companion object {
        private const val PAGE_SIZE = 20
    }
}