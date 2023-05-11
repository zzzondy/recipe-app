package com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.recipe_adding.presentation.R
import com.recipe_adding.presentation.screens.recipe_adding.states.IngredientItem
import com.recipeapp.components.buttons.BackgroundLessButton
import com.recipeapp.components.buttons.BackgroundLessIconButton
import com.recipeapp.components.textfields.DefaultTextField
import com.recipeapp.theme.RecipeAppTheme

@Composable
internal fun IngredientsSection(
    ingredients: List<IngredientItem>,
    onChangedIngredient: (Int, String) -> Unit,
    onChangedIngredientQuantity: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
    onAddNewIngredient: () -> Unit = {},
    onRemoveIngredient: (Int) -> Unit = {},
    isError: Boolean = false,
) {
    val paddings = RecipeAppTheme.paddings

    val focusManager = LocalFocusManager.current

    Column(modifier = modifier.animateContentSize()) {
        Text(
            text = stringResource(R.string.ingredients),
            style = RecipeAppTheme.typography.boldH5,
            color = RecipeAppTheme.colors.neutral100
        )

        if (ingredients.isNotEmpty()) {
            Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(RecipeAppTheme.paddings.small)
        ) {
            ingredients.forEachIndexed { index, ingredientItem ->
                val ingredientTextFieldId = "ingredient_text_field"
                val ingredientQuantityTextFieldId = "ingredient_quantity_text_field"
                val removeButtonId = "remove_button"

                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth(),
                    constraintSet = ConstraintSet {
                        val ingredientTextField = createRefFor(ingredientTextFieldId)
                        val ingredientQuantityTextField =
                            createRefFor(ingredientQuantityTextFieldId)
                        val removeButton = createRefFor(removeButtonId)

                        constrain(ingredientTextField) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)

                            height = Dimension.wrapContent
                            width = Dimension.percent(0.5f)
                        }

                        constrain(ingredientQuantityTextField) {
                            start.linkTo(ingredientTextField.end, paddings.small)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)

                            height = Dimension.wrapContent
                            width = Dimension.percent(0.35f)
                        }

                        constrain(removeButton) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                    }
                ) {
                    DefaultTextField(
                        value = ingredientItem.name,
                        onValueChange = {
                            onChangedIngredient(index, it)
                        },
                        modifier = Modifier.layoutId(ingredientTextFieldId),
                        singleLine = true,
                        placeholder = {
                            Text(text = stringResource(R.string.item_name))
                        },
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        isError = ingredientItem.isNameError
                    )

                    DefaultTextField(
                        value = ingredientItem.quantity,
                        onValueChange = {
                            onChangedIngredientQuantity(index, it)
                        },
                        modifier = Modifier.layoutId(ingredientQuantityTextFieldId),
                        singleLine = true,
                        placeholder = {
                            Text(text = stringResource(R.string.quantity_format))
                        },
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        isError = ingredientItem.isQuantityError,
                    )

                    BackgroundLessIconButton(
                        onClick = {
                            onRemoveIngredient(index)
                        },
                        pressedContentColor = RecipeAppTheme.colors.neutral100,
                        defaultContentColor = RecipeAppTheme.colors.neutral90,
                        modifier = Modifier
                            .layoutId(removeButtonId)
                            .size(RecipeAppTheme.sizes.small)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.minus_border),
                            contentDescription = stringResource(
                                R.string.remove_icon
                            )
                        )
                    }

                }
            }
        }

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.extraSmall))

        BackgroundLessButton(
            onClick = onAddNewIngredient,
            pressedContentColor = if (isError) RecipeAppTheme.colors.error50 else RecipeAppTheme.colors.neutral100,
            defaultContentColor = if (isError) RecipeAppTheme.colors.error100 else RecipeAppTheme.colors.neutral90
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.add_icon)
            )

            Text(
                text = stringResource(R.string.add_new_ingredient),
                style = RecipeAppTheme.typography.boldP
            )
        }
    }
}