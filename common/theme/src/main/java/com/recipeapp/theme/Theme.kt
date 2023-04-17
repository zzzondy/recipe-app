package com.recipeapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun RecipeAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) darkColors else lightColors

    val typography = typography

    val shapes = shapes

    val paddings = paddings

    val sizes = sizes

    CompositionLocalProvider(
        LocalRecipeAppColors provides colors,
        LocalRecipeAppTypography provides typography,
        LocalRecipeAppShapes provides shapes,
        LocalRecipeAppPaddings provides paddings,
        LocalRecipeAppSizes provides sizes,
        content = content
    )
}