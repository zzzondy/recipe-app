package com.recipeapp.components.buttons

import android.os.SystemClock
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
import com.recipeapp.utils.MULTIPLE_CLICKS_PREVENTION_TIME_DELTA
import com.recipeapp.utils.bounceClick

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
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
        modifier = modifier
            .bounceClick(enabled = enabled),
        shape = RecipeAppTheme.shapes.default,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = RecipeAppTheme.colors.primary50,
            disabledBackgroundColor = RecipeAppTheme.colors.neutral20,
            contentColor = if (RecipeAppTheme.colors.isLightTheme) RecipeAppTheme.colors.white0 else RecipeAppTheme.colors.neutral100,
            disabledContentColor = RecipeAppTheme.colors.neutral50,
        ),
        interactionSource = remember { NoRippleInteractionSource() },
        content = content
    )
}

@Preview
@Composable
private fun DefaultButtonPreview() {
    RecipeAppTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium)
            ) {
                Text(text = "Preview", style = RecipeAppTheme.typography.boldP)
            }

            DefaultButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Text(text = "Preview", style = RecipeAppTheme.typography.boldP)
            }

            DefaultButton(
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

            DefaultButton(
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

            DefaultIconButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
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
                    modifier = Modifier.size(RecipeAppTheme.sizes.default)
                )
            }
        }
    }
}