package com.recipe_adding.presentation.screens.meal_type_choosing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.recipeapp.components.screen_states_ui.ErrorScreenState
import com.recipeapp.components.textfields.DefaultTextField
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

    Scaffold(
        topBar = {
            DefaultTextField(
                value = searchingQuery,
                onValueChange = {
                    searchingQuery = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon)
                    )
                },
                trailingIcon = {
                    BackgroundLessIconButton(
                        onClick = {
                            searchingQuery = ""
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear, contentDescription = stringResource(
                                R.string.clear_icon
                            )
                        )
                    }
                },
                placeholder = {
                    Text(text = stringResource(R.string.enter_meal_type))
                }
            )
        },
        backgroundColor = RecipeAppTheme.colors.white0,
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
                        .fillMaxSize(),
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
