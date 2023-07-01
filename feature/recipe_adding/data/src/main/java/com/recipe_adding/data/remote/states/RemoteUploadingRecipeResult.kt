package com.recipe_adding.data.remote.states

sealed interface RemoteUploadingRecipeResult {

    object Success : RemoteUploadingRecipeResult

    object NetworkError : RemoteUploadingRecipeResult

    object OtherError : RemoteUploadingRecipeResult
}