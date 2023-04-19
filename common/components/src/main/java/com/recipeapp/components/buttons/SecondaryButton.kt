package com.recipeapp.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
        shape = RecipeAppTheme.shapes.default,
        border = BorderStroke(
            width = RecipeAppTheme.sizes.extraExtraSmall,
            color = if (enabled) RecipeAppTheme.colors.primary50 else RecipeAppTheme.colors.neutral20
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = RecipeAppTheme.colors.white0,
            contentColor = RecipeAppTheme.colors.primary50,
            disabledBackgroundColor = RecipeAppTheme.colors.white0,
            disabledContentColor = RecipeAppTheme.colors.neutral50
        ),
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

                Spacer(modifier = Modifier.width(RecipeAppTheme.paddings.small))

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

                Spacer(modifier = Modifier.width(RecipeAppTheme.paddings.small))

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(RecipeAppTheme.sizes.default)
                )
            }
        }
    }
}