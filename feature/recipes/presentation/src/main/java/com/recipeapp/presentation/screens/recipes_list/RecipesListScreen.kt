package com.recipeapp.presentation.screens.recipes_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun RecipesListScreen(viewModel: RecipesListScreenViewModel) {
    val recipes = viewModel.recipes.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(recipes) { recipe ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = recipe?.title ?: "")

                Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.extraSmall))

                Text(text = recipe?.description ?: "")
            }
        }
    }
}