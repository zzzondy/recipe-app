package com.recipe_adding.presentation.screens.meal_type_choosing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.domain.use_cases.ObtainMealTypesByPageUseCase
import com.recipe_adding.presentation.screens.meal_type_choosing.states.MealTypeChoosingBottomSheetAction
import com.recipe_adding.presentation.screens.meal_type_choosing.states.MealTypeChoosingBottomSheetEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class MealTypeChoosingBottomSheetViewModel(obtainMealTypesByPageUseCase: ObtainMealTypesByPageUseCase) :
    ViewModel() {

    private val _effect = MutableSharedFlow<MealTypeChoosingBottomSheetEffect>()
    val effect = _effect.asSharedFlow()

    private val queryChannel = Channel<String>(capacity = 1)

    val mealTypes = queryChannel.receiveAsFlow()
        .distinctUntilChanged()
        .sample(SEARCHING_PERIOD)
        .flatMapLatest { searchQuery ->
            obtainMealTypesByPageUseCase(searchQuery = searchQuery)
        }
        .flowOn(Dispatchers.IO)

    init {
        viewModelScope.launch {
            queryChannel.send("")
        }
    }

    fun onDispatchAction(action: MealTypeChoosingBottomSheetAction) {
        when (action) {
            is MealTypeChoosingBottomSheetAction.OnMealTypeClicked -> onMealTypeClicked(action.mealType)

            is MealTypeChoosingBottomSheetAction.OnRetryButtonClicked -> onRetryButtonClicked()

            is MealTypeChoosingBottomSheetAction.OnRefreshButtonClicked -> onRefreshButtonClicked()

            is MealTypeChoosingBottomSheetAction.OnTypingSearchQuery -> onTypingSearchQuery(action.query)

            is MealTypeChoosingBottomSheetAction.OnCloseButtonClicked -> onCloseButtonClicked()
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

    private fun onTypingSearchQuery(query: String) {
        viewModelScope.launch {
            queryChannel.send(query)
        }
    }

    private fun onCloseButtonClicked() {
        viewModelScope.launch {
            _effect.emit(MealTypeChoosingBottomSheetEffect.CloseScreen)
        }
    }

    companion object {
        private const val SEARCHING_PERIOD: Long = 500
    }
}