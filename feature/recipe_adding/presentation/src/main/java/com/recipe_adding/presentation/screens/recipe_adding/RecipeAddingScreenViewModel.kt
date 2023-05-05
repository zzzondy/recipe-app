package com.recipe_adding.presentation.screens.recipe_adding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenAction
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenState
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenStateMachine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeAddingScreenViewModel(private val recipeAddingScreenStateMachine: RecipeAddingScreenStateMachine) :
    ViewModel() {

    private val _state = MutableStateFlow<RecipeAddingScreenState>(RecipeAddingScreenState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            recipeAddingScreenStateMachine.state.collect { newState ->
                _state.update { newState }
            }
        }
    }

    fun dispatchAction(action: RecipeAddingScreenAction) {
        viewModelScope.launch {
            recipeAddingScreenStateMachine.dispatch(action)
        }
    }
}