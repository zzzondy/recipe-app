package com.recipes.data.remote.network

import com.recipes.data.remote.models.ObtainingRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {

    @GET("recipe/")
    suspend fun obtainRecipes(@Query("page") page: Int): ObtainingRecipesResponse
}