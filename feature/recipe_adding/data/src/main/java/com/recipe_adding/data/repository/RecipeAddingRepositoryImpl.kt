package com.recipe_adding.data.repository

import com.recipe_adding.data.remote.repository.RemoteRecipeAddingRepository
import com.recipe_adding.data.util.toDomain
import com.recipe_adding.data.util.toRemote
import com.recipe_adding.domain.models.Recipe
import com.recipe_adding.domain.repository.RecipeAddingRepository
import com.recipe_adding.domain.states.UploadingRecipeResult

class RecipeAddingRepositoryImpl(private val remoteRecipeAddingRepository: RemoteRecipeAddingRepository) :
    RecipeAddingRepository {

    override suspend fun uploadRecipe(recipe: Recipe): UploadingRecipeResult =
        remoteRecipeAddingRepository.uploadRecipe(recipe.toRemote()).toDomain()
}