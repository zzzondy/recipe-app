package com.recipes.domain.repository

import com.recipes.domain.states.ObtainingRecipesResult

interface RecipesRepository {

    suspend fun obtainRecipes(): ObtainingRecipesResult
}