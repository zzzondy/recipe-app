package com.recipe_adding.presentation.screens.recipe_adding

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.recipe_adding.presentation.screens.recipe_adding.components.RecipeAddingScreenTopBar
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenAction
import com.recipe_adding.presentation.screens.recipe_adding.states.RecipeAddingScreenState
import com.recipe_adding.presentation.screens.recipe_adding.states.ui.RecipeAddingScreenContentState
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun RecipeAddingScreen(
    state: RecipeAddingScreenState,
    onDispatchAction: (RecipeAddingScreenAction) -> Unit = {}
) {
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                onDispatchAction(RecipeAddingScreenAction.AddImage(uri))
            }
        }
    )

    Scaffold(
        topBar = {
            RecipeAddingScreenTopBar()
        },
        backgroundColor = RecipeAppTheme.colors.white0,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        when (state) {
            is RecipeAddingScreenState.ContentState -> {
                RecipeAddingScreenContentState(
                    images = state.images,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    onAddImageClicked = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
                )
            }

            else -> {

            }
        }
    }
}

@Preview
@Composable
fun RecipeAddingScreenPreview() {
    RecipeAppTheme {
        RecipeAddingScreen(state = RecipeAddingScreenState.ContentState(emptyList()))
    }
}