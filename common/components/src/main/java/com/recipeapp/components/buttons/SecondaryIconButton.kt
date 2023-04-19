package com.recipeapp.components.buttons

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun SecondaryIconButton(
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
        contentPadding = PaddingValues(1.dp),
        content = content
    )
}


@Preview(showBackground = true)
@Composable
private fun SecondaryIconButtonPreview() {
    RecipeAppTheme {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            SecondaryIconButton() {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }

            SecondaryIconButton(enabled = false) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
    }
}