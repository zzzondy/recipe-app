package com.recipeapp.components.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun DefaultIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        shape = RecipeAppTheme.shapes.default,
        modifier = modifier.size(RecipeAppTheme.sizes.small),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = RecipeAppTheme.colors.primary50,
            disabledBackgroundColor = RecipeAppTheme.colors.neutral20,
            contentColor = RecipeAppTheme.colors.white0,
            disabledContentColor = RecipeAppTheme.colors.neutral50
        ),
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

            DefaultIconButton(modifier = Modifier.padding(RecipeAppTheme.paddings.medium), enabled = false) {
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