package com.recipe_adding.presentation.screens.meal_type_choosing.states

import androidx.paging.PagingData
import com.recipe_adding.domain.models.MealType
import com.recipeapp.utils.UIText
import kotlinx.coroutines.flow.Flow

sealed class MealTypeChoosingBottomSheetState {

    object Loading : MealTypeChoosingBottomSheetState()

    data class Data(val mealTypes: Flow<PagingData<MealType>>) :
        MealTypeChoosingBottomSheetState()

    data class Error(val title: UIText, val subtitle: UIText) : MealTypeChoosingBottomSheetState()
}