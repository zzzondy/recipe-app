package com.recipeapp.components.buttons

import android.os.SystemClock
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.recipeapp.components.NoRippleInteractionSource
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.ClickState
import com.recipeapp.utils.MULTIPLE_CLICKS_PREVENTION_TIME_DELTA
import com.recipeapp.utils.bounceClick

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    var lastClickTime by remember { mutableStateOf(0L) }
    var clickState by remember { mutableStateOf(ClickState.IDLE) }

    val contentColor by animateColorAsState(if (clickState == ClickState.PRESSED) RecipeAppTheme.colors.primary80 else RecipeAppTheme.colors.primary50)
    val backgroundColor by animateColorAsState(if (clickState == ClickState.PRESSED) RecipeAppTheme.colors.primary10 else RecipeAppTheme.colors.white0)

    Button(
        enabled = enabled,
        onClick = {
            if (SystemClock.elapsedRealtime() - lastClickTime >= MULTIPLE_CLICKS_PREVENTION_TIME_DELTA) {
                lastClickTime = SystemClock.elapsedRealtime()
                onClick()
            }
        },
        modifier = modifier
            .bounceClick(enabled = enabled, otherEffect = { clickState = it }),
        shape = RecipeAppTheme.shapes.default,
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
        interactionSource = remember { NoRippleInteractionSource() },
        content = content
    )
}

@Preview
@Composable
private fun SecondaryButtonPreview() {
    RecipeAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium)
            ) {
                Text(text = "Preview", style = RecipeAppTheme.typography.boldP)
            }

            SecondaryButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Text(text = "Preview", style = RecipeAppTheme.typography.boldP)
            }

            SecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium),
                enabled = false
            ) {
                Text(text = "Preview", style = RecipeAppTheme.typography.boldP)

                Spacer(modifier = Modifier.width(RecipeAppTheme.paddings.extraSmall))

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(RecipeAppTheme.sizes.default)
                )
            }

            SecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium)
            ) {
                Text(text = "Preview", style = RecipeAppTheme.typography.boldP)

                Spacer(modifier = Modifier.width(RecipeAppTheme.paddings.extraSmall))

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(RecipeAppTheme.sizes.default)
                )
            }
        }
    }
}