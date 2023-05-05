package com.recipe_adding.presentation.screens.recipe_adding.states

import android.net.Uri
import com.freeletics.flowredux.dsl.FlowReduxStateMachine
import com.freeletics.flowredux.dsl.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.internal.toImmutableList

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class RecipeAddingScreenStateMachine :
    FlowReduxStateMachine<RecipeAddingScreenState, RecipeAddingScreenAction>(initialState = RecipeAddingScreenState.Loading) {

    private val images = mutableListOf<Uri>()

    init {
        spec {
            inState<RecipeAddingScreenState.Loading> {
                onEnter {
                    it.override { RecipeAddingScreenState.ContentState(images.toImmutableList()) }
                }
            }

            inState {
                on { action: RecipeAddingScreenAction.AddImage, state: State<RecipeAddingScreenState.ContentState> ->
                    images.add(action.imageUri)
                    state.mutate { this.copy(images = this@RecipeAddingScreenStateMachine.images.toImmutableList()) }
                }
            }
        }
    }
}