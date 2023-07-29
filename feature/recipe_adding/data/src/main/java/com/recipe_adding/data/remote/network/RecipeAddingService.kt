package com.recipe_adding.data.remote.network

import com.recipe_adding.data.remote.models.RemoteRecipe
import com.recipe_adding.data.remote.models.meal_type.ObtainingMealTypesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RecipeAddingService {

    @POST("recipe/")
    suspend fun uploadRecipe(@Body recipe: RemoteRecipe): Response<RemoteRecipe>

    @GET("food_type/")
    suspend fun obtainMealTypesByPage(
        @Query("limit") pagingLimit: Int,
        @Query("offset") pagingOffset: Long,
        @Query("search") searchQuery: String,
    ): Response<ObtainingMealTypesResponse>
}