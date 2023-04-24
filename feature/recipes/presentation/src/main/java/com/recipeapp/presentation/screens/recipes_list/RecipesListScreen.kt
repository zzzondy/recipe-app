package com.recipeapp.presentation.screens.recipes_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.recipeapp.components.buttons.DefaultButton
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun RecipesListScreen(onClick: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Placeholder", style = RecipeAppTheme.typography.regularLabel)
        DefaultButton(onClick = onClick) {

        }
    }
}