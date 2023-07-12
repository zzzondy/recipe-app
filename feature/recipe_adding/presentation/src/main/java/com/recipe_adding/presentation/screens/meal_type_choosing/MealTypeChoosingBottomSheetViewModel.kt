package com.recipe_adding.presentation.screens.meal_type_choosing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.domain.use_cases.ObtainMealTypesByPageUseCase
import com.recipe_adding.presentation.screens.meal_type_choosing.states.MealTypeChoosingBottomSheetAction
import com.recipe_adding.presentation.screens.meal_type_choosing.states.MealTypeChoosingBottomSheetEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MealTypeChoosingBottomSheetViewModel(obtainMealTypesByPageUseCase: ObtainMealTypesByPageUseCase) :
    ViewModel() {

    val mealTypes = obtainMealTypesByPageUseCase().flowOn(Dispatchers.IO).cachedIn(viewModelScope)

    private val _effect = MutableSharedFlow<MealTypeChoosingBottomSheetEffect>()
    val effect = _effect.asSharedFlow()

    fun onDispatchAction(action: MealTypeChoosingBottomSheetAction) {
        when (action) {
            is MealTypeChoosingBottomSheetAction.OnMealTypeClicked -> onMealTypeClicked(action.mealType)

            is MealTypeChoosingBottomSheetAction.OnRetryButtonClicked -> onRetryButtonClicked()

            is MealTypeChoosingBottomSheetAction.OnRefreshButtonClicked -> onRefreshButtonClicked()
        }
    }

    private fun onMealTypeClicked(mealType: MealType) {
        viewModelScope.launch {
            _effect.emit(MealTypeChoosingBottomSheetEffect.OnMealTypeSelected(mealType))
        }
    }

    private fun onRetryButtonClicked() {
        viewModelScope.launch {
            _effect.emit(MealTypeChoosingBottomSheetEffect.RetryObtainingData)
        }
    }

    private fun onRefreshButtonClicked() {
        viewModelScope.launch {
            _effect.emit(MealTypeChoosingBottomSheetEffect.RefreshData)
        }
    }
}