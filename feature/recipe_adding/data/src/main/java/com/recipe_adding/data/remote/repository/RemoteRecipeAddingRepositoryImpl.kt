package com.recipe_adding.data.remote.repository

import android.util.Log
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
            Log.d("Recipe adding", e.toString())
            RemoteUploadingRecipeResult.NetworkError
        } catch (e: ConnectException) {
            Log.d("Recipe adding", e.toString())
            RemoteUploadingRecipeResult.NetworkError
        } catch (e: Exception) {
            Log.d("Recipe adding", e.toString())
            RemoteUploadingRecipeResult.OtherError
        }
    }

    override suspend fun obtainMealTypesByPage(page: Int): RemoteObtainingMealTypesResult {
        return try {
            val response = recipeAddingService.obtainMealTypesByPage(page)

            Log.d("Recipe adding", response.toString())
            if (response.isSuccessful) {
                RemoteObtainingMealTypesResult.Success(response.body()!!)
            } else {
                RemoteObtainingMealTypesResult.OtherError
            }
        } catch (e: IOException) {
            Log.d("Recipe adding", e.toString())
            RemoteObtainingMealTypesResult.NetworkError
        } catch (e: Exception) {
            Log.d("Recipe adding", e.toString())
            RemoteObtainingMealTypesResult.OtherError
        }
    }
}