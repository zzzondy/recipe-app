package com.recipe_adding.presentation.screens.meal_type_choosing.ui_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import com.recipe_adding.domain.models.MealType
import com.recipeapp.theme.RecipeAppTheme
import kotlin.random.Random

@Composable
fun MealTypeItem(
    mealType: MealType?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = mealType?.name ?: "",
            style = RecipeAppTheme.typography.regularP,
            color = RecipeAppTheme.colors.neutral100,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .then(
                    if (mealType == null) {
                        Modifier
                            .fillMaxWidth(
                                Random
                                    .nextDouble(from = 0.25, until = 1.0)
                                    .toFloat()
                            )
                            .placeholder(
                                visible = true,
                                color = RecipeAppTheme.colors.neutral30,
                                highlight = PlaceholderHighlight.shimmer(),
                                shape = RecipeAppTheme.shapes.medium
                            )
                    } else {
                        Modifier
                    }
                )
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))
    }
}