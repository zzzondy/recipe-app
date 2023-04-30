package com.recipes.data.remote.repository

import com.recipes.data.remote.models.ObtainingRecipesResponse

interface RemoteRecipesRepository {

    suspend fun obtainRecipesWithPage(page: Int): ObtainingRecipesResponse
}