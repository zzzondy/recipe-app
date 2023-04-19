package com.recipeapp.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun BackgroundLessIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(RecipeAppTheme.sizes.small),
        shape = RecipeAppTheme.shapes.default,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = RecipeAppTheme.colors.primary50,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = RecipeAppTheme.colors.neutral50
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        enabled = enabled,
        contentPadding = PaddingValues(1.dp),
        content = content
    )
}