package com.recipe_adding.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.recipe_adding.data.remote.repository.RemoteRecipeAddingRepository
import com.recipe_adding.data.remote.states.RemoteObtainingMealTypesResult
import com.recipe_adding.data.util.toDomain
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.domain.states.ObtainingMealTypesResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealTypesPagingSource(
    private val searchQuery: String,
    private val remoteRecipeAddingRepository: RemoteRecipeAddingRepository,
) : PagingSource<Int, MealType>() {

    override fun getRefreshKey(state: PagingState<Int, MealType>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MealType> =
        withContext(Dispatchers.IO) {
            val currentOffset = (params.key ?: 0)

            val remoteResult = remoteRecipeAddingRepository.obtainMealTypesByPage(
                searchQuery = searchQuery,
                pagingLimit = params.loadSize,
                pagingOffset = currentOffset.toLong()
            )

            val previousPage =
                if (remoteResult is RemoteObtainingMealTypesResult.Success) remoteResult.response.previousPage else null

            val nextPage =
                if (remoteResult is RemoteObtainingMealTypesResult.Success) remoteResult.response.nextPage else null

            val result = remoteResult.toDomain()

            if (result is ObtainingMealTypesResult.Success) {
                LoadResult.Page(
                    data = result.mealTypes,
                    prevKey = if (previousPage == null) null else currentOffset - params.loadSize,
                    nextKey = if (nextPage == null) null else currentOffset + params.loadSize
                )
            } else {
                LoadResult.Error(Exception())
            }
        }
}