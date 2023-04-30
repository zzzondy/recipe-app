package com.recipes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.recipes.data.paging.RecipesPagingSource
import com.recipes.data.remote.repository.RemoteRecipesRepository
import com.recipes.domain.models.Recipe
import com.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class RecipesRepositoryImpl(private val remoteRecipesRepository: RemoteRecipesRepository) :
    RecipesRepository {

    override fun obtainRecipesWithPaging(): Flow<PagingData<Recipe>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                RecipesPagingSource(remoteRecipesRepository)
            }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}