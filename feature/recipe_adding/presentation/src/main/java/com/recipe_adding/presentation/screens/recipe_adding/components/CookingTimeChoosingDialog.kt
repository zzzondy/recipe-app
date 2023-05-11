package com.recipe_adding.presentation.screens.recipe_adding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.recipe_adding.presentation.R
import com.recipeapp.components.buttons.DefaultButton
import com.recipeapp.theme.RecipeAppTheme
import com.sd.lib.compose.wheel_picker.FVerticalWheelPicker
import com.sd.lib.compose.wheel_picker.rememberFWheelPickerState
import kotlinx.coroutines.delay


@Composable
fun CookingTimeChoosingDialog(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    initialValue: Int = 0,
    onChooseClicked: (Int) -> Unit = {}
) {
    if (expanded) {
        val state = rememberFWheelPickerState()
        LaunchedEffect(key1 = state) {
            delay(100)
            state.animateScrollToIndex(initialValue)
        }

        Dialog(onDismissRequest = onDismissRequest, properties = DialogProperties()) {
            Box(
                modifier = Modifier
                    .size(
                        width = 300.dp,
                        height = 240.dp
                    )
                    .background(
                        color = RecipeAppTheme.colors.neutral10,
                        shape = RecipeAppTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(RecipeAppTheme.paddings.medium)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.choose_time_in_minutes),
                        style = RecipeAppTheme.typography.boldP,
                        modifier = Modifier.align(Alignment.Start),
                        color = RecipeAppTheme.colors.neutral100
                    )

                    Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.medium))

                    FVerticalWheelPicker(
                        state = state,
                        count = COOKING_TIME_IN_MINUTES_MAX,
                        modifier = Modifier.width(RecipeAppTheme.sizes.large)
                    ) {
                        Text(text = it.toString(), color = RecipeAppTheme.colors.neutral100)
                    }

                    Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.medium))

                    DefaultButton(
                        onClick = {
                            onChooseClicked(state.currentIndex)
                        }
                    ) {
                        Text(text = stringResource(R.string.choose))
                    }
                }
            }
        }
    }
}

private const val COOKING_TIME_IN_MINUTES_MAX = 999