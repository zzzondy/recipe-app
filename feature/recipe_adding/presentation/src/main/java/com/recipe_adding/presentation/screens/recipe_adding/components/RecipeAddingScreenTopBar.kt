package com.recipe_adding.presentation.screens.recipe_adding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.recipe_adding.presentation.R
import com.recipeapp.components.buttons.BackgroundLessIconButton
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun RecipeAddingScreenTopBar(
    onNavigateBack: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(RecipeAppTheme.paddings.extraSmall)
            .background(RecipeAppTheme.colors.white0)
    ) {
        BackgroundLessIconButton(
            onClick = { onNavigateBack() }
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = stringResource(
                    R.string.arrow_back_icon
                ),
                tint = RecipeAppTheme.colors.neutral100
            )
        }

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.medium))

        Text(
            text = stringResource(R.string.create_recipe),
            style = RecipeAppTheme.typography.boldH4,
            color = RecipeAppTheme.colors.neutral100,
            modifier = Modifier.padding(start = RecipeAppTheme.paddings.extraSmall)
        )
    }
}

@Preview
@Composable
fun RecipeAddingScreenTopBarPreview() {
    RecipeAppTheme {
        RecipeAddingScreenTopBar()
    }
}