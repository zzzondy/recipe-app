package com.recipeapp.components.buttons

import android.os.SystemClock
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.recipeapp.components.NoRippleInteractionSource
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.MULTIPLE_CLICKS_PREVENTION_TIME_DELTA
import com.recipeapp.utils.bounceClick

@Composable
fun DefaultIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    backgroundColor: Color = RecipeAppTheme.colors.primary50,
    contentColor: Color = if (RecipeAppTheme.colors.isLightTheme) RecipeAppTheme.colors.white0 else RecipeAppTheme.colors.neutral100,
    shape: Shape = RecipeAppTheme.shapes.default,
    size: Dp = RecipeAppTheme.sizes.small,
    content: @Composable RowScope.() -> Unit
) {
    var lastClickTime by remember { mutableStateOf(0L) }

    Button(
        enabled = enabled,
        onClick = {
            if (SystemClock.elapsedRealtime() - lastClickTime >= MULTIPLE_CLICKS_PREVENTION_TIME_DELTA) {
                lastClickTime = SystemClock.elapsedRealtime()
                onClick()
            }
        },
        shape = shape,
        modifier = modifier
            .size(size)
            .bounceClick(enabled = enabled),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = RecipeAppTheme.colors.neutral20,
            disabledContentColor = RecipeAppTheme.colors.neutral50
        ),
        interactionSource = remember { NoRippleInteractionSource() },
        contentPadding = PaddingValues(1.dp),
        content = content
    )
}


@Preview
@Composable
private fun DefaultIconButtonPreview() {
    RecipeAppTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            DefaultIconButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = RecipeAppTheme.colors.white0,
                    modifier = Modifier.size(RecipeAppTheme.sizes.default)
                )
            }

            DefaultIconButton(
                modifier = Modifier.padding(RecipeAppTheme.paddings.medium),
                enabled = false
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = RecipeAppTheme.colors.white0,
                    modifier = Modifier.size(RecipeAppTheme.sizes.default)
                )
            }
        }
    }
}