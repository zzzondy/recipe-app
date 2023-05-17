package com.recipe_adding.presentation.screens.recipe_adding.states

import android.net.Uri
import com.freeletics.flowredux.dsl.FlowReduxStateMachine
import com.freeletics.flowredux.dsl.State
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.domain.use_cases.ValidateQuantityUseCase
import com.recipe_adding.presentation.R
import com.recipeapp.utils.UIText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import okhttp3.internal.toImmutableList

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class RecipeAddingScreenStateMachine(private val validateQuantityUseCase: ValidateQuantityUseCase) :
    FlowReduxStateMachine<RecipeAddingScreenState, RecipeAddingScreenAction>(initialState = RecipeAddingScreenState.Loading) {

    private val _effect = MutableSharedFlow<RecipeAddingScreenEffect>()
    val effect = _effect.asSharedFlow()

    private val urisOfImages = mutableListOf<Uri>()
    private var cookingTimeInMinutes = 0
    private val ingredients = mutableListOf<IngredientItem>()

    init {
        spec {
            inState<RecipeAddingScreenState.Loading> {
                onEnter {
                    it.override {
                        RecipeAddingScreenState.ContentState(
                            images = urisOfImages.toImmutableList(),
                            recipeName = "",
                            cookingTime = UIText.PluralsResource(
                                R.plurals.minutes_plural,
                                cookingTimeInMinutes,
                                cookingTimeInMinutes
                            ),
                            mealTypes = listOf(
                                MealType("Meal type", isEditable = true),
                                MealType("Soap"),
                                MealType("First"),
                                MealType("Second"),
                                MealType("Third"),
                                MealType("Beef")
                            ),
                            selectedMealType = MealType("Meal type", isEditable = true),
                            customMealType = "",
                            description = "",
                            ingredients = ingredients.toImmutableList(),
                            isImagesError = false,
                            isNameError = false,
                            isCookingTimeError = false,
                            isDescriptionError = false,
                            isMealTypeError = false,
                            isIngredientsError = false,
                        )
                    }
                }
            }

            inState {
                on { action: RecipeAddingScreenAction.AddImage, state: State<RecipeAddingScreenState.ContentState> ->
                    urisOfImages.addAll(0, action.imageUris)
                    state.mutate {
                        this.copy(
                            images = urisOfImages.toImmutableList(),
                            isImagesError = urisOfImages.isEmpty() && this.isImagesError
                        )
                    }
                }

                on { action: RecipeAddingScreenAction.RemoveImage, state: State<RecipeAddingScreenState.ContentState> ->
                    urisOfImages.remove(action.imageUri)
                    state.mutate { this.copy(images = urisOfImages.toImmutableList()) }
                }

                on { action: RecipeAddingScreenAction.OnChangedRecipeName, state: State<RecipeAddingScreenState.ContentState> ->
                    state.mutate { this.copy(recipeName = action.newName, isNameError = false) }
                }

                on { action: RecipeAddingScreenAction.OnCookingTimeChanged, state: State<RecipeAddingScreenState.ContentState> ->
                    cookingTimeInMinutes = action.cookingTimeInMinutes

                    state.mutate {
                        this.copy(
                            cookingTime = UIText.PluralsResource(
                                R.plurals.minutes_plural,
                                this@RecipeAddingScreenStateMachine.cookingTimeInMinutes,
                                this@RecipeAddingScreenStateMachine.cookingTimeInMinutes,
                            ),
                            isCookingTimeError = false,
                        )
                    }
                }

                on { action: RecipeAddingScreenAction.OnMealTypeChanged, state: State<RecipeAddingScreenState.ContentState> ->
                    state.mutate {
                        this.copy(
                            selectedMealType = action.mealType,
                            isMealTypeError = action.mealType.isEditable && this.customMealType.isEmpty()
                        )
                    }
                }

                on { action: RecipeAddingScreenAction.OnChangeCustomMealType, state: State<RecipeAddingScreenState.ContentState> ->
                    state.mutate {
                        this.copy(
                            customMealType = action.name,
                            isMealTypeError = action.name.isEmpty()
                        )
                    }
                }

                on { action: RecipeAddingScreenAction.OnDescriptionChanged, state: State<RecipeAddingScreenState.ContentState> ->
                    state.mutate {
                        this.copy(
                            description = action.newDescription,
                            isDescriptionError = false,
                        )
                    }
                }

                on { _: RecipeAddingScreenAction.AddNewIngredientClicked, state: State<RecipeAddingScreenState.ContentState> ->
                    if (ingredients.indexOfFirst { it.name == "" && it.quantity == "" } == -1) {
                        ingredients.add(IngredientItem("", ""))

                        state.mutate {
                            this.copy(
                                ingredients = this@RecipeAddingScreenStateMachine.ingredients.toImmutableList(),
                                isIngredientsError = false,
                            )
                        }
                    } else {
                        state.noChange()
                    }
                }

                on { action: RecipeAddingScreenAction.OnChangedIngredient, state: State<RecipeAddingScreenState.ContentState> ->
                    ingredients[action.index] =
                        ingredients[action.index].copy(name = action.newIngredient)

                    handleIngredientsRepetitions()

                    state.mutate {
                        this.copy(
                            ingredients = this@RecipeAddingScreenStateMachine.ingredients.toImmutableList()
                        )
                    }
                }

                on { action: RecipeAddingScreenAction.OnChangedIngredientQuantity, state: State<RecipeAddingScreenState.ContentState> ->
                    if (action.newQuantity.length > ValidateQuantityUseCase.lengthLimit) {
                        state.noChange()
                    } else {
                        ingredients[action.index] =
                            ingredients[action.index].copy(
                                quantity = action.newQuantity,
                                isQuantityError = if (action.newQuantity.isEmpty()) {
                                    false
                                } else {
                                    !validateQuantityUseCase(action.newQuantity)
                                }
                            )

                        state.mutate {
                            this.copy(
                                ingredients = this@RecipeAddingScreenStateMachine.ingredients.toImmutableList()
                            )
                        }
                    }
                }

                on { action: RecipeAddingScreenAction.OnRemoveIngredient, state: State<RecipeAddingScreenState.ContentState> ->
                    ingredients.removeAt(action.index)

                    handleIngredientsRepetitions()

                    state.mutate {
                        this.copy(
                            ingredients = this@RecipeAddingScreenStateMachine.ingredients.toImmutableList()
                        )
                    }
                }

                on { _: RecipeAddingScreenAction.OnSaveRecipeClicked, state: State<RecipeAddingScreenState.ContentState> ->
                    handleIngredientsErrors()

                    state.mutate {
                        this.copy(
                            ingredients = this@RecipeAddingScreenStateMachine.ingredients.toImmutableList(),
                            isImagesError = this@RecipeAddingScreenStateMachine.urisOfImages.isEmpty(),
                            isNameError = this.recipeName.isEmpty(),
                            isCookingTimeError = this@RecipeAddingScreenStateMachine.cookingTimeInMinutes == 0,
                            isDescriptionError = this.description.isEmpty(),
                            isMealTypeError = this.selectedMealType.isEditable && this.customMealType.isEmpty(),
                            isIngredientsError = this@RecipeAddingScreenStateMachine.ingredients.isEmpty() || this@RecipeAddingScreenStateMachine.ingredients.indexOfFirst { it.name.isEmpty() || it.quantity.isEmpty() } != -1
                        )
                    }
                }

                onActionEffect { _: RecipeAddingScreenAction.OnCloseScreenClicked, _: Any ->
                    _effect.emit(RecipeAddingScreenEffect.NavigateBack)
                }
            }
        }
    }

    private fun getIndicesOfTheSameIngredients(): List<Int> {
        val indices = mutableListOf<Int>()

        ingredients.forEachIndexed { index, ingredientItem ->
            if (ingredients.filter { it.name == ingredientItem.name }.size != 1) {
                indices.add(index)
            }
        }

        return indices
    }

    private fun handleIngredientsRepetitions() {
        val theSameIngredientsIndices = getIndicesOfTheSameIngredients()

        ingredients.indices.forEach { index ->
            if (theSameIngredientsIndices.contains(index)) {
                ingredients[index] = ingredients[index].copy(isNameError = true)
            } else {
                ingredients[index] = ingredients[index].copy(isNameError = false)
            }
        }
    }

    private fun handleIngredientsErrors() {
        ingredients.forEachIndexed { index, ingredientItem ->
            ingredients[index] = ingredients[index].copy(
                isNameError = ingredientItem.name.isBlank(),
                isQuantityError = !validateQuantityUseCase(ingredientItem.quantity),
            )
        }
    }
}