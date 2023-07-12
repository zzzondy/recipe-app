package com.recipe_adding.presentation.screens.meal_type_choosing.states.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.presentation.screens.meal_type_choosing.ui_components.MealTypeItem
import com.recipeapp.components.screen_states_ui.AppendPagingErrorState
import com.recipeapp.components.screen_states_ui.AppendPagingLoadingState
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.bounceClick

@Composable
fun MealTypeChoosingContentState(
    mealTypes: LazyPagingItems<MealType>,
    modifier: Modifier = Modifier,
    onMealTypeClicked: (MealType) -> Unit = {},
    onRetryButtonClicked: () -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(mealTypes, key = { it.id }) { mealType ->
            MealTypeItem(
                mealType = mealType,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = RecipeAppTheme.paddings.medium)
                    .bounceClick {
                        onMealTypeClicked(mealType!!)
                    }
            )
        }

        item(key = "append_state") {
            if (mealTypes.loadState.append is LoadState.Loading) {
                AppendPagingLoadingState(modifier = Modifier.padding(RecipeAppTheme.paddings.medium))
            } else if (mealTypes.loadState.append is LoadState.Error) {
                AppendPagingErrorState(
                    modifier = Modifier.padding(RecipeAppTheme.paddings.medium),
                    onRefresh = onRetryButtonClicked
                )
            }
        }
    }
}

