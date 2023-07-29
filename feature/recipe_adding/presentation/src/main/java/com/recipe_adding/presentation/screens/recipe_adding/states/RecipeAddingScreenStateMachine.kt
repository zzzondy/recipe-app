package com.recipe_adding.presentation.screens.recipe_adding.states

import android.graphics.Bitmap
import com.freeletics.flowredux.dsl.ChangedState
import com.freeletics.flowredux.dsl.FlowReduxStateMachine
import com.freeletics.flowredux.dsl.State
import com.recipe_adding.domain.models.Ingredient
import com.recipe_adding.domain.models.Recipe
import com.recipe_adding.domain.states.UploadingRecipeResult
import com.recipe_adding.domain.use_cases.UploadRecipeUseCase
import com.recipe_adding.domain.use_cases.ValidateQuantityUseCase
import com.recipe_adding.presentation.R
import com.recipeapp.utils.UIText
import com.recipeapp.utils.toBase64
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import okhttp3.internal.toImmutableList

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class RecipeAddingScreenStateMachine(
    private val validateQuantityUseCase: ValidateQuantityUseCase,
    private val uploadRecipeUseCase: UploadRecipeUseCase
) :
    FlowReduxStateMachine<RecipeAddingScreenState, RecipeAddingScreenAction>(initialState = RecipeAddingScreenState.Loading) {

    private val _effect = MutableSharedFlow<RecipeAddingScreenEffect>()
    val effect = _effect.asSharedFlow()

    private val images = mutableListOf<Bitmap>()
    private var cookingTimeInMinutes = 0
    private val ingredients = mutableListOf<IngredientItem>()

    init {
        spec {
            inState {
                onActionEffect { _: RecipeAddingScreenAction.OnCloseScreen, _: Any ->
                    _effect.emit(RecipeAddingScreenEffect.NavigateBack)
                }
            }

            inState<RecipeAddingScreenState.Loading> {
                onEnter {
                    it.override {
                        RecipeAddingScreenState.ContentState(
                            images = images.toImmutableList(),
                            recipeName = "",
                            cookingTime = UIText.PluralsResource(
                                R.plurals.minutes_plural,
                                cookingTimeInMinutes,
                                cookingTimeInMinutes
                            ),
                            selectedMealTypeName = UIText.StringResource(R.string.not_selected),
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
                    onAddImage(action = action, state = state)
                }

                on { action: RecipeAddingScreenAction.RemoveImage, state: State<RecipeAddingScreenState.ContentState> ->
                    onRemoveImage(action = action, state = state)
                }

                on { action: RecipeAddingScreenAction.OnChangedRecipeName, state: State<RecipeAddingScreenState.ContentState> ->
                    onChangedRecipeName(action = action, state = state)
                }

                on { action: RecipeAddingScreenAction.OnCookingTimeChanged, state: State<RecipeAddingScreenState.ContentState> ->
                    onCookingTimeChanged(action = action, state = state)
                }

                on { action: RecipeAddingScreenAction.OnDescriptionChanged, state: State<RecipeAddingScreenState.ContentState> ->
                    onDescriptionChanged(action = action, state = state)
                }

                on { _: RecipeAddingScreenAction.OnAddNewIngredient, state: State<RecipeAddingScreenState.ContentState> ->
                    onAddNewIngredient(state = state)
                }

                on { action: RecipeAddingScreenAction.OnChangedIngredientName, state: State<RecipeAddingScreenState.ContentState> ->
                    onChangedIngredientName(action = action, state = state)
                }

                on { action: RecipeAddingScreenAction.OnChangedIngredientQuantity, state: State<RecipeAddingScreenState.ContentState> ->
                    onChangedIngredientQuantity(action = action, state = state)
                }

                on { action: RecipeAddingScreenAction.OnRemoveIngredient, state: State<RecipeAddingScreenState.ContentState> ->
                    onRemoveIngredient(action = action, state = state)
                }

                on { _: RecipeAddingScreenAction.OnSaveRecipe, state: State<RecipeAddingScreenState.ContentState> ->
                    onSaveRecipe(state = state)
                }

                on { action: RecipeAddingScreenAction.OnMealTypeSelected, state: State<RecipeAddingScreenState.ContentState> ->
                    onMealTypeSelected(action, state)
                }

                onActionEffect { _: RecipeAddingScreenAction.OnMealTypeSectionClicked, _: RecipeAddingScreenState.ContentState ->
                    _effect.emit(RecipeAddingScreenEffect.OpenMealTypesChoosingDialog)
                }
            }

            inState {
                on { _: RecipeAddingScreenAction.OnTryAgainClicked, state: State<RecipeAddingScreenState.ErrorState> ->
                    state.override { RecipeAddingScreenState.Loading }
                }
            }
        }
    }

    private fun onAddImage(
        action: RecipeAddingScreenAction.AddImage,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        images.add(0, action.image)
        return state.mutate {
            this.copy(
                images = this@RecipeAddingScreenStateMachine.images.toImmutableList(),
                isImagesError = this@RecipeAddingScreenStateMachine.images.isEmpty() && this.isImagesError,
                isAddImageButtonAvailable = this@RecipeAddingScreenStateMachine.images.size < MAX_COUNT_OF_IMAGES
            )
        }
    }

    private fun onRemoveImage(
        action: RecipeAddingScreenAction.RemoveImage,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        images.removeAt(action.index)
        return state.mutate {
            this.copy(
                images = this@RecipeAddingScreenStateMachine.images.toImmutableList(),
                isAddImageButtonAvailable = this@RecipeAddingScreenStateMachine.images.size < MAX_COUNT_OF_IMAGES
            )
        }
    }

    private fun onChangedRecipeName(
        action: RecipeAddingScreenAction.OnChangedRecipeName,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        return state.mutate { this.copy(recipeName = action.newName, isNameError = false) }
    }

    private fun onCookingTimeChanged(
        action: RecipeAddingScreenAction.OnCookingTimeChanged,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        cookingTimeInMinutes = action.cookingTimeInMinutes

        return state.mutate {
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

    private fun onMealTypeSelected(
        action: RecipeAddingScreenAction.OnMealTypeSelected,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        return state.mutate {
            this.copy(
                selectedMealTypeName = UIText.DynamicText(action.mealTypeName),
                isMealTypeError = false,
            )
        }
    }

    private fun onDescriptionChanged(
        action: RecipeAddingScreenAction.OnDescriptionChanged,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        return state.mutate {
            this.copy(
                description = action.newDescription,
                isDescriptionError = false,
            )
        }
    }

    private fun onAddNewIngredient(
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        return if (ingredients.indexOfFirst { it.name == "" && it.quantity == "" } == -1) {
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

    private fun onChangedIngredientName(
        action: RecipeAddingScreenAction.OnChangedIngredientName,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        ingredients[action.index] =
            ingredients[action.index].copy(name = action.newIngredientName)

        handleIngredientsRepetitions()

        return state.mutate {
            this.copy(
                ingredients = this@RecipeAddingScreenStateMachine.ingredients.toImmutableList()
            )
        }
    }

    private fun onRemoveIngredient(
        action: RecipeAddingScreenAction.OnRemoveIngredient,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        ingredients.removeAt(action.index)

        handleIngredientsRepetitions()

        return state.mutate {
            this.copy(
                ingredients = this@RecipeAddingScreenStateMachine.ingredients.toImmutableList()
            )
        }
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

    private fun getIndicesOfTheSameIngredients(): List<Int> {
        val indices = mutableListOf<Int>()

        ingredients.forEachIndexed { index, ingredientItem ->
            if (ingredients.filter { it.name == ingredientItem.name }.size != 1) {
                indices.add(index)
            }
        }

        return indices
    }

    private fun onChangedIngredientQuantity(
        action: RecipeAddingScreenAction.OnChangedIngredientQuantity,
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        return if (action.newQuantity.length > ValidateQuantityUseCase.lengthLimit) {
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

    private suspend fun onSaveRecipe(
        state: State<RecipeAddingScreenState.ContentState>
    ): ChangedState<RecipeAddingScreenState> {
        handleIngredientsErrors()

        val isImagesError = images.isEmpty()
        val isNameError = state.snapshot.recipeName.isEmpty()
        val isCookingTimeError = cookingTimeInMinutes == 0
        val isDescriptionError = state.snapshot.description.isEmpty()
        val isMealTypeError = state.snapshot.selectedMealTypeName is UIText.StringResource
        val isIngredientsError =
            ingredients.isEmpty() || ingredients.indexOfFirst { it.name.isEmpty() || it.quantity.isEmpty() } != -1

        if (
            !isImagesError && !isIngredientsError && !isNameError &&
            !isCookingTimeError && !isDescriptionError &&
            !isMealTypeError
        ) {
            _effect.emit(RecipeAddingScreenEffect.ShowLoadingDialog)
            val result = uploadRecipeUseCase(
                Recipe(
                    mealType = (state.snapshot.selectedMealTypeName as UIText.DynamicText).value,
                    ingredients = ingredients.map {
                        Ingredient(
                            name = it.name,
                            quantity = it.quantity
                        )
                    },
                    cookingTime = cookingTimeInMinutes,
                    description = state.snapshot.description,
                    name = state.snapshot.recipeName,
                    images = images.map { it.toBase64() }
                )
            )

            when (result) {
                is UploadingRecipeResult.Success -> {
                    _effect.emit(RecipeAddingScreenEffect.NavigateBackOnSuccessfulResult)
                }

                is UploadingRecipeResult.NetworkError -> {
                    _effect.emit(
                        RecipeAddingScreenEffect.ShowErrorSnackBar(
                            title = UIText.StringResource(R.string.no_internet_connection),
                            subtitle = UIText.StringResource(R.string.check_your_connection)
                        )
                    )
                }

                is UploadingRecipeResult.OtherError -> {
                    _effect.emit(
                        RecipeAddingScreenEffect.ShowErrorSnackBar(
                            title = UIText.StringResource(R.string.some_error),
                            subtitle = UIText.StringResource(R.string.some_error_details)
                        )
                    )
                }
            }
        }

        return state.mutate {
            this.copy(
                ingredients = this@RecipeAddingScreenStateMachine.ingredients.toImmutableList(),
                isImagesError = isImagesError,
                isNameError = isNameError,
                isCookingTimeError = isCookingTimeError,
                isDescriptionError = isDescriptionError,
                isMealTypeError = isMealTypeError,
                isIngredientsError = isIngredientsError,
            )
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

    companion object {
        private const val MAX_COUNT_OF_IMAGES = 10
    }
}