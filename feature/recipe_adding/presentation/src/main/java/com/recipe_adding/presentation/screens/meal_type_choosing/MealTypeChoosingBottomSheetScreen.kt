package com.recipe_adding.presentation.screens.meal_type_choosing

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.presentation.R
import com.recipe_adding.presentation.navigation.RecipeAddingFeatureNavigationApi
import com.recipe_adding.presentation.screens.meal_type_choosing.states.MealTypeChoosingBottomSheetAction
import com.recipe_adding.presentation.screens.meal_type_choosing.states.MealTypeChoosingBottomSheetEffect
import com.recipe_adding.presentation.screens.meal_type_choosing.states.ui.MealTypeChoosingContentState
import com.recipe_adding.presentation.screens.meal_type_choosing.states.ui.MealTypeChoosingLoadingState
import com.recipeapp.components.buttons.BackgroundLessIconButton
import com.recipeapp.components.screen_states_ui.screens.ErrorScreenState
import com.recipeapp.components.textfields.SearchTextField
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.collectAsEffect

@Composable
fun MealTypeChoosingBottomSheetScreen(
    navController: NavController,
    viewModel: MealTypeChoosingBottomSheetViewModel,
) {
    val mealTypes = viewModel.mealTypes.collectAsLazyPagingItems()

    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is MealTypeChoosingBottomSheetEffect.OnMealTypeSelected -> {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        key = RecipeAddingFeatureNavigationApi.SELECTED_MEAL_TYPE_NAME,
                        value = effect.mealType.name
                    )
                navController.popBackStack()
            }

            is MealTypeChoosingBottomSheetEffect.RetryObtainingData -> {
                mealTypes.retry()
            }

            is MealTypeChoosingBottomSheetEffect.RefreshData -> {
                mealTypes.refresh()
            }

            is MealTypeChoosingBottomSheetEffect.CloseScreen -> {
                navController.popBackStack()
            }
        }
    }

    MealTypeChoosingBottomSheetContent(
        mealTypes = mealTypes,
        onDispatchAction = viewModel::onDispatchAction
    )
}

@Composable
private fun MealTypeChoosingBottomSheetContent(
    mealTypes: LazyPagingItems<MealType>,
    onDispatchAction: (MealTypeChoosingBottomSheetAction) -> Unit = {},
) {
    var searchingQuery by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                focusManager.clearFocus()
                return super.onPostScroll(consumed, available, source)
            }
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackgroundLessIconButton(
                    modifier = Modifier.padding(
                        start = RecipeAppTheme.paddings.extraSmall,
                        top = RecipeAppTheme.paddings.medium,
                        bottom = RecipeAppTheme.paddings.medium
                    ),
                    onClick = {
                        onDispatchAction(MealTypeChoosingBottomSheetAction.OnCloseButtonClicked)
                    },
                    pressedContentColor = RecipeAppTheme.colors.neutral100,
                    defaultContentColor = RecipeAppTheme.colors.neutral90,
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon),
                    )
                }

                Spacer(modifier = Modifier.width(RecipeAppTheme.paddings.small))

                SearchTextField(
                    value = searchingQuery,
                    placeholderText = stringResource(R.string.enter_meal_type),
                    onValueChanged = {
                        searchingQuery = it
                        onDispatchAction(MealTypeChoosingBottomSheetAction.OnTypingSearchQuery(it))
                    },
                    onClearInput = {
                        searchingQuery = ""
                        onDispatchAction(MealTypeChoosingBottomSheetAction.OnTypingSearchQuery(""))
                    },
                    onSearchClicked = {
                        focusManager.clearFocus()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = RecipeAppTheme.paddings.medium,
                            top = RecipeAppTheme.paddings.medium,
                            bottom = RecipeAppTheme.paddings.medium
                        )
                )
            }
        },
        backgroundColor = RecipeAppTheme.colors.white0,
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
    ) { paddingValues ->
        when (mealTypes.loadState.refresh) {
            is LoadState.Loading -> {
                MealTypeChoosingLoadingState(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )
            }

            is LoadState.Error -> {
                ErrorScreenState(
                    title = stringResource(R.string.no_internet_connection),
                    subtitle = stringResource(R.string.check_your_connection),
                    modifier = Modifier.fillMaxSize(),
                    onTryAgainButtonClicked = {
                        onDispatchAction(MealTypeChoosingBottomSheetAction.OnRefreshButtonClicked)
                    }
                )
            }

            else -> {
                MealTypeChoosingContentState(
                    mealTypes = mealTypes,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .nestedScroll(nestedScrollConnection),
                    onMealTypeClicked = { mealType ->
                        onDispatchAction(
                            MealTypeChoosingBottomSheetAction.OnMealTypeClicked(
                                mealType
                            )
                        )
                    },
                    onRetryButtonClicked = {
                        onDispatchAction(MealTypeChoosingBottomSheetAction.OnRetryButtonClicked)
                    }
                )
            }
        }
    }
}
