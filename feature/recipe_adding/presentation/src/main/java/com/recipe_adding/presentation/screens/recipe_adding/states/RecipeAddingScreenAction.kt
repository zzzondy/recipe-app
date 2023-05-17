package com.recipe_adding.presentation.screens.recipe_adding.states

import android.net.Uri
import com.recipe_adding.domain.models.MealType

sealed interface RecipeAddingScreenAction {

    data class AddImage(val imageUris: List<Uri>) : RecipeAddingScreenAction

    data class RemoveImage(val imageUri: Uri) : RecipeAddingScreenAction

    data class ReplaceImage(val imageUri: Uri, val newImageUri: Uri) : RecipeAddingScreenAction

    data class OnCookingTimeChanged(val cookingTimeInMinutes: Int) : RecipeAddingScreenAction

    data class OnMealTypeChanged(val mealType: MealType) : RecipeAddingScreenAction

    data class OnChangeCustomMealType(val name: String) : RecipeAddingScreenAction

    data class OnDescriptionChanged(val newDescription: String) : RecipeAddingScreenAction

    data class OnChangedRecipeName(val newName: String) : RecipeAddingScreenAction

    object AddNewIngredientClicked : RecipeAddingScreenAction

    data class OnRemoveIngredient(val index: Int) : RecipeAddingScreenAction

    data class OnChangedIngredient(val index: Int, val newIngredient: String) :
        RecipeAddingScreenAction

    data class OnChangedIngredientQuantity(val index: Int, val newQuantity: String) :
        RecipeAddingScreenAction

    object OnSaveRecipeClicked : RecipeAddingScreenAction

    object OnCloseScreenClicked : RecipeAddingScreenAction
}
