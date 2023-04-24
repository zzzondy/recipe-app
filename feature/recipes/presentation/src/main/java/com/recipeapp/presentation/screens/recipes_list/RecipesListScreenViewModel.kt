package com.recipeapp.presentation.screens.recipes_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipes.domain.use_cases.ObtainRecipesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipesListScreenViewModel(private val obtainRecipesUseCase: ObtainRecipesUseCase) :
    ViewModel() {

    fun onClick() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("RecipesViewModel", obtainRecipesUseCase().toString())
        }
    }
}