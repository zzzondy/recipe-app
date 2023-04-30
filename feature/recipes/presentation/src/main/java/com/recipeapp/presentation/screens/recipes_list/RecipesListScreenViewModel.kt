package com.recipeapp.presentation.screens.recipes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.recipes.domain.use_cases.ObtainRecipesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class RecipesListScreenViewModel(obtainRecipesUseCase: ObtainRecipesUseCase) :
    ViewModel() {

    val recipes = obtainRecipesUseCase().flowOn(Dispatchers.IO).cachedIn(viewModelScope)
}