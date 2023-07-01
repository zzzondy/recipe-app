package com.recipe_adding.data.remote.repository

import com.recipe_adding.data.remote.models.RemoteRecipe
import com.recipe_adding.data.remote.states.RemoteUploadingRecipeResult

interface RemoteRecipeAddingRepository {

    suspend fun uploadRecipe(recipe: RemoteRecipe): RemoteUploadingRecipeResult
}