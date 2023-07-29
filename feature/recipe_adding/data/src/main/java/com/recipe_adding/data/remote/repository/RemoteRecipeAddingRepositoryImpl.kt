package com.recipe_adding.data.remote.repository

import com.recipe_adding.data.remote.models.RemoteRecipe
import com.recipe_adding.data.remote.network.RecipeAddingService
import com.recipe_adding.data.remote.states.RemoteObtainingMealTypesResult
import com.recipe_adding.data.remote.states.RemoteUploadingRecipeResult
import java.io.IOException
import java.net.ConnectException

class RemoteRecipeAddingRepositoryImpl(private val recipeAddingService: RecipeAddingService) :
    RemoteRecipeAddingRepository {

    override suspend fun uploadRecipe(recipe: RemoteRecipe): RemoteUploadingRecipeResult {
        return try {
            recipeAddingService.uploadRecipe(recipe)
            RemoteUploadingRecipeResult.Success
        } catch (e: IOException) {
            RemoteUploadingRecipeResult.NetworkError
        } catch (e: ConnectException) {
            RemoteUploadingRecipeResult.NetworkError
        } catch (e: Exception) {
            RemoteUploadingRecipeResult.OtherError
        }
    }

    override suspend fun obtainMealTypesByPage(
        searchQuery: String,
        pagingLimit: Int,
        pagingOffset: Long
    ): RemoteObtainingMealTypesResult {
        return try {
            val response = recipeAddingService.obtainMealTypesByPage(
                searchQuery = searchQuery,
                pagingLimit = pagingLimit,
                pagingOffset = pagingOffset
            )

            if (response.isSuccessful) {
                RemoteObtainingMealTypesResult.Success(response.body()!!)
            } else {
                RemoteObtainingMealTypesResult.OtherError
            }
        } catch (e: IOException) {
            RemoteObtainingMealTypesResult.NetworkError
        } catch (e: Exception) {
            RemoteObtainingMealTypesResult.OtherError
        }
    }
}