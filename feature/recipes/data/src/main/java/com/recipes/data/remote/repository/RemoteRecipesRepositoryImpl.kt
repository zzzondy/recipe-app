package com.recipes.data.remote.repository

import com.recipes.data.remote.models.ObtainingRecipesResponse
import com.recipes.data.remote.network.RecipesService

class RemoteRecipesRepositoryImpl(private val recipesService: RecipesService) :
    RemoteRecipesRepository {

    override suspend fun obtainRecipesWithPage(page: Int): ObtainingRecipesResponse =
        recipesService.obtainRecipes(page)
}