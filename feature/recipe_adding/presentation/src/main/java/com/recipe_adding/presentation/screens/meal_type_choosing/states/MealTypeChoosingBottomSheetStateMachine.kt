package com.recipe_adding.presentation.screens.meal_type_choosing.states

import com.freeletics.flowredux.dsl.FlowReduxStateMachine

class MealTypeChoosingBottomSheetStateMachine :
    FlowReduxStateMachine<MealTypeChoosingBottomSheetState, MealTypeChoosingBottomSheetAction>(
        initialState = MealTypeChoosingBottomSheetState.Loading
    ) {

    init {
        spec {

        }
    }
}