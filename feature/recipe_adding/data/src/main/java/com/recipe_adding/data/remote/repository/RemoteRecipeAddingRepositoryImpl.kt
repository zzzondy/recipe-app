package com.recipe_adding.data.remote.repository

import android.util.Log
import com.recipe_adding.data.remote.models.RemoteRecipe
import com.recipe_adding.data.remote.network.RecipeAddingService
import com.recipe_adding.data.remote.states.RemoteUploadingRecipeResult
import java.io.IOException

class RemoteRecipeAddingRepositoryImpl(private val recipeAddingService: RecipeAddingService) :
    RemoteRecipeAddingRepository {

    override suspend fun uploadRecipe(recipe: RemoteRecipe): RemoteUploadingRecipeResult {
        return try {
            recipeAddingService.uploadRecipe(recipe)
            RemoteUploadingRecipeResult.Success
        } catch (e: IOException) {
            Log.d("Recipe adding", e.toString())
            RemoteUploadingRecipeResult.NetworkError
        } catch (e: Exception) {
            Log.d("Recipe adding", e.toString())
            RemoteUploadingRecipeResult.OtherError
        }
    }
}