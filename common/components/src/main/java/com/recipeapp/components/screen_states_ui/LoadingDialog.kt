package com.recipeapp.components.screen_states_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun LoadingDialog() {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .background(
                    color = RecipeAppTheme.colors.neutral10,
                    shape = RecipeAppTheme.shapes.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = RecipeAppTheme.colors.primary50)
        }
    }
}

@Preview
@Composable
fun LoadingDialogPreview() {
    RecipeAppTheme {
        LoadingDialog()
    }
}