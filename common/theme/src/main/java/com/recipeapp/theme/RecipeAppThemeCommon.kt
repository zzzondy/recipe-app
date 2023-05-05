package com.recipeapp.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class RecipeAppColors(
    val isLightTheme: Boolean,
    // Neutral
    val neutral100: Color,
    val neutral90: Color,
    val neutral80: Color,
    val neutral70: Color,
    val neutral60: Color,
    val neutral50: Color,
    val neutral40: Color,
    val neutral30: Color,
    val neutral20: Color,
    val neutral10: Color,
    val white0: Color,

    // Primary
    val primary100: Color,
    val primary90: Color,
    val primary80: Color,
    val primary70: Color,
    val primary60: Color,
    val primary50: Color,
    val primary40: Color,
    val primary30: Color,
    val primary20: Color,
    val primary10: Color,
    val primary0: Color,

    // Secondary
    val secondary100: Color,
    val secondary90: Color,
    val secondary80: Color,
    val secondary70: Color,
    val secondary60: Color,
    val secondary50: Color,
    val secondary40: Color,
    val secondary30: Color,
    val secondary20: Color,
    val secondary10: Color,
    val secondary0: Color,

    // Rating
    val rating100: Color,

    // Error
    val error100: Color,
    val error10: Color,

    // Success
    val success100: Color,
    val success10: Color
)

data class RecipeAppTypography(
    // Regular
    val regularHeading: TextStyle,
    val regularH1: TextStyle,
    val regularH2: TextStyle,
    val regularH3: TextStyle,
    val regularH4: TextStyle,
    val regularH5: TextStyle,
    val regularP: TextStyle,
    val regularLabel: TextStyle,
    val regularSmall: TextStyle,
    val regularTiny: TextStyle,

    // Bold
    val boldHeading: TextStyle,
    val boldH1: TextStyle,
    val boldH2: TextStyle,
    val boldH3: TextStyle,
    val boldH4: TextStyle,
    val boldH5: TextStyle,
    val boldP: TextStyle,
    val boldLabel: TextStyle,
    val boldSmall: TextStyle,
    val boldTiny: TextStyle,
)

data class RecipeAppPaddings(
    val default: Dp,
    val extraExtraSmall: Dp,
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)

data class RecipeAppSizes(
    val default: Dp,
    val extraExtraSmall: Dp,
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
    val imageWidth: Dp,
    val imageHeight: Dp,
)

data class RecipeAppShapes(
    val default: Shape,
    val extraSmall: Shape,
    val small: Shape,
    val medium: Shape,
    val large: Shape,
    val extraLarge: Shape
)

object RecipeAppTheme {
    val colors: RecipeAppColors
        @Composable
        get() = LocalRecipeAppColors.current

    val typography: RecipeAppTypography
        @Composable
        get() = LocalRecipeAppTypography.current

    val paddings: RecipeAppPaddings
        @Composable
        get() = LocalRecipeAppPaddings.current

    val shapes: RecipeAppShapes
        @Composable
        get() = LocalRecipeAppShapes.current

    val sizes: RecipeAppSizes
        @Composable
        get() = LocalRecipeAppSizes.current
}

internal object RecipeAppRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor(): Color = RippleTheme.defaultRippleColor(
        contentColor = RecipeAppTheme.colors.primary100,
        lightTheme = RecipeAppTheme.colors.isLightTheme
    )

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        contentColor = RecipeAppTheme.colors.primary100,
        lightTheme = RecipeAppTheme.colors.isLightTheme
    )
}

val LocalRecipeAppColors = staticCompositionLocalOf<RecipeAppColors> { error("No colors provided") }

val LocalRecipeAppTypography =
    staticCompositionLocalOf<RecipeAppTypography> { error("No typography provided") }

val LocalRecipeAppPaddings =
    staticCompositionLocalOf<RecipeAppPaddings> { error("No paddings provided") }

val LocalRecipeAppSizes = staticCompositionLocalOf<RecipeAppSizes> { error("No sizes provided") }

val LocalRecipeAppShapes = staticCompositionLocalOf<RecipeAppShapes> { error("No shapes provided") }