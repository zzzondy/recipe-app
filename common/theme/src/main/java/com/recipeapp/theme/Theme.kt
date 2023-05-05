package com.recipeapp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.white0,
            darkIcons = !isDarkTheme,
            isNavigationBarContrastEnforced = false
        )
    }

    CompositionLocalProvider(
        LocalRecipeAppColors provides colors,
        LocalRecipeAppTypography provides typography,
        LocalRecipeAppShapes provides shapes,
        LocalRecipeAppPaddings provides paddings,
        LocalRecipeAppSizes provides sizes,
        LocalRippleTheme provides RecipeAppRippleTheme,
        content = content
    )
}