package com.recipes.data.remote.repository

import com.recipes.data.remote.states.RemoteObtainingRecipesResult

interface RemoteRecipesRepository {

    suspend fun obtainRecipes(): RemoteObtainingRecipesResult
}