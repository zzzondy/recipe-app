package com.recipeapp.components.buttons

import androidx.compose.material.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = RecipeAppTheme.colors.primary50,
        contentColor = RecipeAppTheme.colors.white0,
        modifier = modifier,
        content = content
    )
}