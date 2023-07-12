package com.recipe_adding.presentation.screens.recipe_adding.states

import android.graphics.Bitmap
import android.net.Uri

sealed interface RecipeAddingScreenAction {

    data class AddImages(val imageUris: List<Bitmap>) : RecipeAddingScreenAction

    data class RemoveImage(val index: Int) : RecipeAddingScreenAction

    data class ReplaceImage(val imageUri: Uri, val newImageUri: Uri) : RecipeAddingScreenAction

    data class OnCookingTimeChanged(val cookingTimeInMinutes: Int) : RecipeAddingScreenAction

    data class OnDescriptionChanged(val newDescription: String) : RecipeAddingScreenAction

    data class OnChangedRecipeName(val newName: String) : RecipeAddingScreenAction

    object OnMealTypeSectionClicked : RecipeAddingScreenAction

    data class OnMealTypeSelected(val mealTypeName: String) : RecipeAddingScreenAction

    object OnAddNewIngredient : RecipeAddingScreenAction

    data class OnRemoveIngredient(val index: Int) : RecipeAddingScreenAction

    data class OnChangedIngredientName(val index: Int, val newIngredientName: String) :
        RecipeAddingScreenAction

    data class OnChangedIngredientQuantity(val index: Int, val newQuantity: String) :
        RecipeAddingScreenAction

    object OnSaveRecipe : RecipeAddingScreenAction

    object OnCloseScreen : RecipeAddingScreenAction


    object OnTryAgainClicked : RecipeAddingScreenAction
}
