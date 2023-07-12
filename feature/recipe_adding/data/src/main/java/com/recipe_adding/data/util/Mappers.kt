package com.recipe_adding.data.util

import com.recipe_adding.data.remote.models.RemoteIngredient
import com.recipe_adding.data.remote.models.RemoteIngredientName
import com.recipe_adding.data.remote.models.RemoteRecipe
import com.recipe_adding.data.remote.models.RemoteRecipeImage
import com.recipe_adding.data.remote.models.meal_type.RemoteMealType
import com.recipe_adding.data.remote.states.RemoteObtainingMealTypesResult
import com.recipe_adding.data.remote.states.RemoteUploadingRecipeResult
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.domain.models.Recipe
import com.recipe_adding.domain.states.ObtainingMealTypesResult
import com.recipe_adding.domain.states.UploadingRecipeResult

fun RemoteUploadingRecipeResult.toDomain() = when (this) {
    RemoteUploadingRecipeResult.Success -> UploadingRecipeResult.Success

    RemoteUploadingRecipeResult.NetworkError -> UploadingRecipeResult.NetworkError

    RemoteUploadingRecipeResult.OtherError -> UploadingRecipeResult.OtherError
}

fun Recipe.toRemote(): RemoteRecipe = RemoteRecipe(
    mealType = RemoteMealType(name = this.mealType),
    ingredients = this.ingredients.map { ingredient ->
        RemoteIngredient(
            ingredientName = RemoteIngredientName(
                name = ingredient.name
            ), quantity = ingredient.quantity
        )
    },
    cookingTime = this.cookingTime,
    name = this.name,
    description = this.description,
    images = this.images.map { image -> RemoteRecipeImage(image = image) },
)

fun RemoteObtainingMealTypesResult.toDomain() = when (this) {

    is RemoteObtainingMealTypesResult.Success -> ObtainingMealTypesResult.Success(mealTypes = response.results.map { it.toDomain() })

    is RemoteObtainingMealTypesResult.NetworkError -> ObtainingMealTypesResult.NetworkError

    is RemoteObtainingMealTypesResult.OtherError -> ObtainingMealTypesResult.OtherError
}

fun RemoteMealType.toDomain() = MealType(
    id = id,
    name = name,
)