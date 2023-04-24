package com.recipes.data.remote.repository

import com.recipes.data.remote.network.RecipesService
import com.recipes.data.remote.states.RemoteObtainingRecipesResult
import java.io.IOException

class RemoteRecipesRepositoryImpl(private val recipesService: RecipesService) :
    RemoteRecipesRepository {

    override suspend fun obtainRecipes(): RemoteObtainingRecipesResult {
        return try {
            val result = recipesService.obtainRecipes()
            if (result.isSuccessful) {
                RemoteObtainingRecipesResult.Success(result.body()!!.recipes)
            } else {
                RemoteObtainingRecipesResult.NetworkError
            }
        } catch (e: IOException) {
            RemoteObtainingRecipesResult.NetworkError
        } catch (e: Exception) {
            RemoteObtainingRecipesResult.OtherError
        }
    }
}