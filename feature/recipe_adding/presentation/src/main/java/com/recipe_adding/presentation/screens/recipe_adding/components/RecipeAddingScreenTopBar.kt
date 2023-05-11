package com.recipe_adding.presentation.screens.recipe_adding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            onClick = { onNavigateBack() },
            pressedContentColor = RecipeAppTheme.colors.neutral100,
            defaultContentColor = RecipeAppTheme.colors.neutral90,
            modifier = Modifier.size(RecipeAppTheme.sizes.small)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(
                    R.string.arrow_back_icon
                )
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