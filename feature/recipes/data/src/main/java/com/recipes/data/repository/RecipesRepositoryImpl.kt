package com.recipes.data.repository

import com.recipes.data.remote.repository.RemoteRecipesRepository
import com.recipes.data.utils.toDomain
import com.recipes.domain.repository.RecipesRepository
import com.recipes.domain.states.ObtainingRecipesResult

class RecipesRepositoryImpl(private val remoteRecipesRepository: RemoteRecipesRepository) :
    RecipesRepository {

    override suspend fun obtainRecipes(): ObtainingRecipesResult =
        remoteRecipesRepository.obtainRecipes().toDomain()
}