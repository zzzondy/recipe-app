package com.recipeapp.components.screen_states_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun AppendPagingLoadingState(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = RecipeAppTheme.colors.primary50)
    }
}