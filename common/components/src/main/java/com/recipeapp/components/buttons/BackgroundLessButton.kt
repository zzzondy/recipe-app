package com.recipeapp.components.buttons

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun BackgroundLessButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        shape = RecipeAppTheme.shapes.default,
        colors = ButtonDefaults.textButtonColors(
            contentColor = RecipeAppTheme.colors.primary50,
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

            BackgroundLessButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium), enabled = false) {
                Text(text = "Preview")
            }

            BackgroundLessButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Text(text = "Preview")

                Spacer(modifier = Modifier.width(RecipeAppTheme.paddings.small))

                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }

            BackgroundLessIconButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium)) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }

            BackgroundLessIconButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium), enabled = false) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}