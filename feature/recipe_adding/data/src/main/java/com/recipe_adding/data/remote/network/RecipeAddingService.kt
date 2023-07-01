package com.recipe_adding.data.remote.network

import com.recipe_adding.data.remote.models.RemoteRecipe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecipeAddingService {

    @POST("recipe/")
    suspend fun uploadRecipe(@Body recipe: RemoteRecipe): Response<RemoteRecipe>

    @GET("food_type/")
    suspend fun obtainMealTypesByPage(@Query("page") page: Int)
}