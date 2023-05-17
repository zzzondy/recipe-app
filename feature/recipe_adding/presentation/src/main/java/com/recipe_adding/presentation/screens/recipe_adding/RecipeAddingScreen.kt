package com.recipe_adding.presentation.screens.recipe_adding

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import com.recipe_adding.presentation.screens.recipe_adding.components.CookingTimeChoosingDialog
import com.recipe_adding.presentation.screens.recipe_adding.components.RecipeAddingScreenTopBar
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenAction
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenState
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.RecipeAddingScreenContentState
import com.recipeapp.components.top_app_bar.rememberTopAppBarState
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.UIText
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

@Composable
fun RecipeAddingScreen(
    state: RecipeAddingScreenState,
    onDispatchAction: (RecipeAddingScreenAction) -> Unit = {}
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            onDispatchAction(RecipeAddingScreenAction.AddImage(uris))
        }
    )

    val topAppBarState = rememberTopAppBarState()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            topAppBarState.scrollTopLimitReached =
                listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
            topAppBarState.scrollOffset = topAppBarState.scrollOffset - available.y
            return Offset(0f, topAppBarState.consumed)
        }

        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
            if (available.y > 0) {
                scope.launch {
                    animateDecay(
                        initialValue = topAppBarState.height + topAppBarState.offset,
                        initialVelocity = available.y,
                        animationSpec = FloatExponentialDecaySpec()
                    ) { value, _ ->
                        topAppBarState.scrollTopLimitReached =
                            listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                        topAppBarState.scrollOffset =
                            topAppBarState.scrollOffset - (value - (topAppBarState.height + topAppBarState.offset))
                        if (topAppBarState.scrollOffset == 0f) scope.coroutineContext.cancelChildren()
                    }
                }
            }

            return super.onPostFling(consumed, available)
        }
    }

    var isDialogExpanded by remember { mutableStateOf(false) }

    CookingTimeChoosingDialog(
        initialValue = if (state is RecipeAddingScreenState.ContentState) state.cookingTime.asString()
            .split(" ")[0].toInt() else 0,
        expanded = isDialogExpanded,
        onDismissRequest = { isDialogExpanded = false },
        onChooseClicked = {
            onDispatchAction(RecipeAddingScreenAction.OnCookingTimeChanged(it))
            isDialogExpanded = false
        }
    )

    Scaffold(
        topBar = {
            RecipeAddingScreenTopBar(
                progress = topAppBarState.progress,
                onNavigateBack = {
                    onDispatchAction(RecipeAddingScreenAction.OnCloseScreenClicked)
                },
                modifier = Modifier
                    .height(with(LocalDensity.current) { topAppBarState.height.toDp() })
            )
        },
        backgroundColor = RecipeAppTheme.colors.white0,
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) { paddingValues ->
        when (state) {
            is RecipeAddingScreenState.ContentState -> {
                RecipeAddingScreenContentState(
                    listState = listState,
                    images = state.images,
                    recipeName = state.recipeName,
                    cookingTime = state.cookingTime.asString(),
                    mealTypes = state.mealTypes,
                    selectedMealType = state.selectedMealType,
                    customMealType = state.customMealType,
                    description = state.description,
                    ingredients = state.ingredients,
                    isMealTypeError = state.isMealTypeError,
                    isImagesError = state.isImagesError,
                    isNameError = state.isNameError,
                    isCookingTimeError = state.isCookingTimeError,
                    isDescriptionError = state.isDescriptionError,
                    isIngredientsError = state.isIngredientsError,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    onAddImageClicked = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    onRemoveImageClicked = { uri ->
                        onDispatchAction(RecipeAddingScreenAction.RemoveImage(uri))
                    },
                    onChangedName = { recipeName ->
                        onDispatchAction(RecipeAddingScreenAction.OnChangedRecipeName(recipeName))
                    },
                    onAddNewIngredient = { onDispatchAction(RecipeAddingScreenAction.AddNewIngredientClicked) },
                    onChangedIngredient = { index, newIngredient ->
                        onDispatchAction(
                            RecipeAddingScreenAction.OnChangedIngredient(
                                index = index, newIngredient = newIngredient
                            )
                        )
                    },
                    onChangedIngredientQuantity = { index, quantity ->
                        onDispatchAction(
                            RecipeAddingScreenAction.OnChangedIngredientQuantity(
                                index = index,
                                newQuantity = quantity
                            )
                        )
                    },
                    onRemoveIngredient = { index ->
                        onDispatchAction(RecipeAddingScreenAction.OnRemoveIngredient(index = index))
                    },
                    onSaveRecipe = {
                        onDispatchAction(RecipeAddingScreenAction.OnSaveRecipeClicked)
                    },
                    onCookingTimeClicked = {
                        isDialogExpanded = true
                    },
                    onDescriptionChanged = { newDescription ->
                        onDispatchAction(
                            RecipeAddingScreenAction.OnDescriptionChanged(
                                newDescription
                            )
                        )
                    },
                    onMealTypeChanged = { mealType ->
                        onDispatchAction(RecipeAddingScreenAction.OnMealTypeChanged(mealType))
                    },
                    onChangedCustomMealType = { name ->
                        onDispatchAction(RecipeAddingScreenAction.OnChangeCustomMealType(name))
                    }
                )
            }

            else -> {
            }
        }
    }
}


@Preview
@Composable
fun RecipeAddingScreenPreview() {
    RecipeAppTheme {
        RecipeAddingScreen(
            state = RecipeAddingScreenState.ContentState(
                images = emptyList(),
                recipeName = "",
                cookingTime = UIText.DynamicText("1 минут"),
                mealTypes = emptyList(),
                description = "",
                ingredients = emptyList(),
            )
        )
    }
}