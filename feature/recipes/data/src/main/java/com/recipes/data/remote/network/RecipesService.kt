package com.recipes.data.remote.network

import com.recipes.data.remote.models.ObtainingRecipesResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipesService {

    @GET("recipe")
    suspend fun obtainRecipes(): Response<ObtainingRecipesResponse>
}