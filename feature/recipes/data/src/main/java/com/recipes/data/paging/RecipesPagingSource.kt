package com.recipes.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.recipes.data.remote.models.ObtainingRecipesResponse
import com.recipes.data.remote.repository.RemoteRecipesRepository
import com.recipes.data.utils.toDomain
import com.recipes.domain.models.Recipe

class RecipesPagingSource(private val remoteRecipesRepository: RemoteRecipesRepository) :
    PagingSource<Int, Recipe>() {

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val currentPage = params.key ?: 1
            val response = obtainRecipesWithPage(currentPage)

            LoadResult.Page(
                data = response.results.map { it.toDomain() },
                prevKey = if (response.previous == null) null else currentPage - 1,
                nextKey = if (response.next == null) null else currentPage + 1
            )
        } catch (e: Exception) {
            Log.d("Paging", e.toString())
            LoadResult.Error(e)
        }
    }

    private suspend fun obtainRecipesWithPage(page: Int): ObtainingRecipesResponse =
        remoteRecipesRepository.obtainRecipesWithPage(page)
}