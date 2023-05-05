package com.recipeapp.components.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.ClickState
import com.recipeapp.utils.bounceClick

@Composable
fun BackgroundLessButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    pressedContentColor: Color = RecipeAppTheme.colors.primary70,
    defaultContentColor: Color = RecipeAppTheme.colors.primary50,
    content: @Composable RowScope.() -> Unit
) {
    var clickState by remember { mutableStateOf(ClickState.IDLE) }

    val contentColor by animateColorAsState(if (clickState == ClickState.PRESSED) pressedContentColor else defaultContentColor)

    TextButton(
        onClick = onClick,
        modifier = modifier
            .bounceClick(enabled = enabled, otherEffect = { clickState = it }),
        shape = RecipeAppTheme.shapes.default,
        colors = ButtonDefaults.textButtonColors(
            contentColor = contentColor,
            disabledContentColor = RecipeAppTheme.colors.neutral50
        ),
        enabled = enabled,
        content = content
    )
}


@Preview
@Composable
private fun BackgroundLessButtonPreview() {
    RecipeAppTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            BackgroundLessButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Text(text = "Preview")
            }

            BackgroundLessButton(
                modifier = Modifier.padding(RecipeAppTheme.paddings.medium),
                enabled = false
            ) {
                Text(text = "Preview")
            }

            BackgroundLessButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Text(text = "Preview")

                Spacer(modifier = Modifier.width(RecipeAppTheme.paddings.extraSmall))

                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }

            BackgroundLessIconButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }

            BackgroundLessIconButton(
                modifier = Modifier.padding(RecipeAppTheme.paddings.medium),
                enabled = false
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }

            BackgroundLessIconButton(
                modifier = Modifier.padding(RecipeAppTheme.paddings.medium),
                pressedContentColor = RecipeAppTheme.colors.neutral90,
                defaultContentColor = RecipeAppTheme.colors.neutral100
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}