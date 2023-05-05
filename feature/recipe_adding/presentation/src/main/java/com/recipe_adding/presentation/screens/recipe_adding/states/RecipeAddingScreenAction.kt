package com.recipe_adding.presentation.screens.recipe_adding.states

import android.net.Uri

sealed interface RecipeAddingScreenAction {
    data class AddImage(val imageUri: Uri) : RecipeAddingScreenAction
}
