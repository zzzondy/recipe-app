package com.recipe_adding.presentation.screens.recipe_adding

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.recipe_adding.presentation.screens.recipe_adding.components.CookingTimeChoosingDialog
import com.recipe_adding.presentation.screens.recipe_adding.components.RecipeAddingScreenTopBar
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenAction
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenState
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.RecipeAddingScreenContentState
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.UIText

@Composable
fun RecipeAddingScreen(
    state: RecipeAddingScreenState,
    onDispatchAction: (RecipeAddingScreenAction) -> Unit = {}
) {
    var progress by rememberSaveable { mutableStateOf(0f) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            onDispatchAction(RecipeAddingScreenAction.AddImage(uris))
        }
    )

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                Log.d("Scroll", "onPostScroll: consumed: $consumed available: $available")
                if (consumed.y == 0f) return Offset.Zero

                val scrollProgress = -consumed.y / 200
                progress = if (scrollProgress > 0) {
                    (scrollProgress + progress).coerceAtMost(1f)
                } else {
                    (scrollProgress + progress).coerceAtLeast(0f)
                }
                return super.onPostScroll(consumed, available, source)
            }
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
            RecipeAddingScreenTopBar(progress = progress)
        },
        backgroundColor = RecipeAppTheme.colors.white0,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        when (state) {
            is RecipeAddingScreenState.ContentState -> {
                RecipeAddingScreenContentState(
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
                        .fillMaxSize()
                        .padding(paddingValues)
                        .nestedScroll(nestedScrollConnection)
                    ,
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