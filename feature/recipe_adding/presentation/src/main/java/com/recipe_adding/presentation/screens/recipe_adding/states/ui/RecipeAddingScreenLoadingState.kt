package com.recipe_adding.presentation.screens.recipe_adding.states.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import com.recipe_adding.presentation.R
import com.recipeapp.components.buttons.BackgroundLessButton
import com.recipeapp.components.buttons.DefaultButton
import com.recipeapp.components.buttons.DefaultIconButton
import com.recipeapp.components.textfields.DefaultTextField
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun RecipeAddingScreenLoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .testTag(RecipeAddingScreenLoadingStateTestingTags.RECIPE_ADDING_SCREEN_LOADING_STATE_ROOT)
    ) {
        LazyColumn(
            userScrollEnabled = false,
            modifier = Modifier.weight(1f)
        ) {
            item(key = "image_section") {
                ImageSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(RecipeAppTheme.sizes.imageHeight)
                )
            }

            item(key = "name_section") {
                NameSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(RecipeAppTheme.paddings.medium)
                )
            }

            item(key = "cooking_time_section") {
                CookingTimeSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(RecipeAppTheme.paddings.medium)
                )
            }

            item(key = "meal_type_section") {
                MealTypeSection(
                    modifier = Modifier
                        .padding(RecipeAppTheme.paddings.medium)
                        .fillMaxWidth()
                )
            }

            item(key = "description_Section") {
                DescriptionSection(
                    modifier = Modifier
                        .padding(RecipeAppTheme.paddings.medium)
                        .fillMaxWidth()
                )
            }

            item(key = "ingredients_section") {
                IngredientsSection(
                    modifier = Modifier
                        .padding(RecipeAppTheme.paddings.medium)
                        .fillMaxWidth()
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            contentAlignment = Alignment.Center
        ) {
            DefaultButton(
                modifier = Modifier
                    .padding(
                        horizontal = RecipeAppTheme.paddings.medium,
                        vertical = RecipeAppTheme.paddings.small
                    )
                    .fillMaxSize()
                    .placeholder(
                        visible = true,
                        color = RecipeAppTheme.colors.neutral30,
                        highlight = PlaceholderHighlight.shimmer(),
                        shape = RecipeAppTheme.shapes.default
                    ),
                content = {}
            )
        }
    }
}


@Composable
private fun ImageSection(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.End) {
        DefaultIconButton(
            modifier = Modifier
                .padding(end = RecipeAppTheme.paddings.medium)
                .placeholder(
                    visible = true,
                    color = RecipeAppTheme.colors.neutral30,
                    highlight = PlaceholderHighlight.shimmer(),
                    shape = RecipeAppTheme.shapes.default
                ),
            content = {}
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))

        LazyRow(
            userScrollEnabled = false,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            item(key = "placeholder") {
                Box(
                    modifier = Modifier
                        .size(
                            width = RecipeAppTheme.sizes.imageWidth,
                            height = RecipeAppTheme.sizes.imageHeight
                        )
                        .placeholder(
                            visible = true,
                            color = RecipeAppTheme.colors.neutral30,
                            highlight = PlaceholderHighlight.shimmer(),
                            shape = RecipeAppTheme.shapes.medium
                        )
                )
            }
        }
    }
}


@Composable
private fun NameSection(modifier: Modifier = Modifier) {
    DefaultTextField(
        readOnly = true,
        value = "",
        onValueChange = {},
        modifier = modifier
            .placeholder(
                visible = true,
                color = RecipeAppTheme.colors.neutral30,
                highlight = PlaceholderHighlight.shimmer(),
                shape = RecipeAppTheme.shapes.default
            )
    )
}


@Composable
private fun CookingTimeSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(RecipeAppTheme.sizes.large)
            .placeholder(
                visible = true,
                color = RecipeAppTheme.colors.neutral30,
                highlight = PlaceholderHighlight.shimmer(),
                shape = RecipeAppTheme.shapes.medium
            )
    )
}


@Composable
private fun MealTypeSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.meal_type),
            modifier = Modifier.placeholder(
                visible = true,
                color = RecipeAppTheme.colors.neutral30,
                highlight = PlaceholderHighlight.shimmer(),
                shape = RecipeAppTheme.shapes.medium
            ),
            style = RecipeAppTheme.typography.boldH5
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))

        DefaultTextField(
            readOnly = true,
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    color = RecipeAppTheme.colors.neutral30,
                    highlight = PlaceholderHighlight.shimmer(),
                    shape = RecipeAppTheme.shapes.default
                )
        )
    }
}


@Composable
private fun DescriptionSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.description),
            modifier = Modifier.placeholder(
                visible = true,
                color = RecipeAppTheme.colors.neutral30,
                highlight = PlaceholderHighlight.shimmer(),
                shape = RecipeAppTheme.shapes.medium
            ),
            style = RecipeAppTheme.typography.boldH5
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))

        DefaultTextField(
            readOnly = true,
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .placeholder(
                    visible = true,
                    color = RecipeAppTheme.colors.neutral30,
                    highlight = PlaceholderHighlight.shimmer(),
                    shape = RecipeAppTheme.shapes.default
                )
        )
    }
}


@Composable
private fun IngredientsSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.ingredients),
            modifier = Modifier.placeholder(
                visible = true,
                color = RecipeAppTheme.colors.neutral30,
                highlight = PlaceholderHighlight.shimmer(),
                shape = RecipeAppTheme.shapes.medium
            ),
            style = RecipeAppTheme.typography.boldH5
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.medium))

        BackgroundLessButton(
            modifier = Modifier.placeholder(
                visible = true,
                color = RecipeAppTheme.colors.neutral30,
                highlight = PlaceholderHighlight.shimmer(),
                shape = RecipeAppTheme.shapes.medium
            ),
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

object RecipeAddingScreenLoadingStateTestingTags {
    const val RECIPE_ADDING_SCREEN_LOADING_STATE_ROOT = "RECIPE_ADDING_SCREEN_LOADING_STATE_ROOT"
}


@Preview
@Composable
fun RecipeAddingScreenLoadingStatePreview() {
    RecipeAppTheme {
        RecipeAddingScreenLoadingState(modifier = Modifier.fillMaxSize())
    }
}