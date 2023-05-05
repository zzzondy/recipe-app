package com.recipe_adding.presentation.screens.recipe_adding.states

import android.net.Uri
import com.recipeapp.utils.UIText

sealed class RecipeAddingScreenState {

    object Loading : RecipeAddingScreenState()

    data class ContentState(val images: List<Uri>) : RecipeAddingScreenState()

    data class Error(val message: UIText) : RecipeAddingScreenState()
}