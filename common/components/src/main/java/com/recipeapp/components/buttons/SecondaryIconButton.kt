package com.recipeapp.components.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.ClickState
import com.recipeapp.utils.bounceClick

@Composable
fun SecondaryIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    var clickState by remember { mutableStateOf(ClickState.IDLE) }

    val contentColor by animateColorAsState(if (clickState == ClickState.PRESSED) RecipeAppTheme.colors.primary80 else RecipeAppTheme.colors.primary50)
    val backgroundColor by animateColorAsState(if (clickState == ClickState.PRESSED) RecipeAppTheme.colors.primary10 else RecipeAppTheme.colors.white0)

    Button(
        enabled = enabled,
        onClick = onClick,
        shape = RecipeAppTheme.shapes.default,
        modifier = modifier
            .size(RecipeAppTheme.sizes.small)
            .bounceClick(enabled = enabled, otherEffect = { clickState = it }),
        border = BorderStroke(
            width = RecipeAppTheme.sizes.extraExtraSmall,
            color = if (enabled) contentColor else RecipeAppTheme.colors.neutral20
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = RecipeAppTheme.colors.white0,
            disabledContentColor = RecipeAppTheme.colors.neutral50
        ),
        contentPadding = PaddingValues(1.dp),
        content = content
    )
}


@Preview(showBackground = true)
@Composable
private fun SecondaryIconButtonPreview() {
    RecipeAppTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            SecondaryIconButton {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }

            SecondaryIconButton(enabled = false) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}