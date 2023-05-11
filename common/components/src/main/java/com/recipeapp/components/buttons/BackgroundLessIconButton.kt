package com.recipeapp.components.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.recipeapp.components.NoRippleInteractionSource
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.ClickState
import com.recipeapp.utils.bounceClick

@Composable
fun BackgroundLessIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    pressedContentColor: Color = RecipeAppTheme.colors.primary70,
    defaultContentColor: Color = RecipeAppTheme.colors.primary50,
    size: Dp = RecipeAppTheme.sizes.small,
    content: @Composable RowScope.() -> Unit
) {
    var clickState by remember { mutableStateOf(ClickState.IDLE) }

    val contentColor by animateColorAsState(if (clickState == ClickState.PRESSED) pressedContentColor else defaultContentColor)

    Button(
        onClick = onClick,
        modifier = modifier
            .size(size)
            .bounceClick(
                enabled = enabled,
                otherEffect = { clickState = it }
            ),
        shape = RecipeAppTheme.shapes.default,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = contentColor,
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
        contentPadding = PaddingValues(0.dp),
        interactionSource = remember { NoRippleInteractionSource() },
        content = content
    )
}