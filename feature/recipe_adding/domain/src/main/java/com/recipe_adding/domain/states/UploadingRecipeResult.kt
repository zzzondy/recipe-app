package com.recipe_adding.domain.states

sealed interface UploadingRecipeResult {

    object Success : UploadingRecipeResult

    object NetworkError : UploadingRecipeResult

    object OtherError : UploadingRecipeResult
}