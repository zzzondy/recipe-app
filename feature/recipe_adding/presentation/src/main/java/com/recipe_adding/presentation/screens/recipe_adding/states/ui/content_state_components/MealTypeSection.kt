package com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.recipe_adding.domain.models.MealType
import com.recipe_adding.presentation.R
import com.recipeapp.components.textfields.DefaultTextField
import com.recipeapp.theme.RecipeAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MealTypeSection(
    mealTypes: List<MealType>,
    modifier: Modifier = Modifier,
    selectedMealType: MealType = mealTypes[0],
    customMealType: String = "",
    isError: Boolean = false,
    onMealTypeChanged: (MealType) -> Unit = {},
    onChangedCustomMealType: (String) -> Unit = {},
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val expandButtonRotation by animateFloatAsState(if (isExpanded) 180F else 0F)

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.meal_type),
            color = RecipeAppTheme.colors.neutral100,
            style = RecipeAppTheme.typography.boldH5
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
                focusManager.clearFocus()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultTextField(
                value = if (!selectedMealType.isEditable) selectedMealType.name else customMealType,
                onValueChange = {
                    if (selectedMealType.isEditable) {
                        onChangedCustomMealType(it)
                    }
                },
                readOnly = !selectedMealType.isEditable,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.expand_more),
                        contentDescription = stringResource(R.string.expand_icon),
                        tint = RecipeAppTheme.colors.primary50,
                        modifier = Modifier.rotate(expandButtonRotation)
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        isExpanded = false
                    }
                ),
                placeholder = {
                    if (selectedMealType.isEditable) {
                        Text(text = stringResource(R.string.enter_type))
                    }
                },
                isError = isError,
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                    focusManager.clearFocus()
                },
                modifier = Modifier.background(
                    color = RecipeAppTheme.colors.white0,
                )
            ) {
                mealTypes.forEach { mealType ->
                    DropdownMenuItem(
                        onClick = {
                            onMealTypeChanged(mealType)
                            isExpanded = false
                            focusManager.clearFocus()
                        }
                    ) {
                        Text(
                            text = mealType.name,
                            style = RecipeAppTheme.typography.regularLabel,
                            color = RecipeAppTheme.colors.neutral100
                        )
                    }
                }
            }
        }
    }
}