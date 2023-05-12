package com.recipe_adding.presentation.screens.recipe_adding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.recipe_adding.presentation.R
import com.recipeapp.components.buttons.BackgroundLessIconButton
import com.recipeapp.theme.RecipeAppTheme

@OptIn(ExperimentalMotionApi::class)
@Composable
fun RecipeAddingScreenTopBar(
    progress: Float,
    onNavigateBack: () -> Unit = {}
) {
    val paddings = RecipeAppTheme.paddings

    MotionLayout(
        motionScene = MotionScene {
            val backArrowButton = createRefFor(BACK_ARROW_BUTTON_ID)
            val title = createRefFor(TITLE_ID)

            defaultTransition(
                from = constraintSet {
                    constrain(backArrowButton) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }

                    constrain(title) {
                        start.linkTo(parent.start, paddings.extraSmall)
                        top.linkTo(backArrowButton.bottom, paddings.medium)
                        bottom.linkTo(parent.bottom)
                    }
                },
                to = constraintSet {
                    constrain(backArrowButton) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    }

                    constrain(title) {
                        start.linkTo(backArrowButton.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                }
            ) {
                keyAttributes(title) {
                    frame(0) {
                        scaleX = 1f
                        scaleY = 1f
                    }

                    frame(25) {
                        scaleX = 0.98f
                        scaleY = 0.98f
                    }

                    frame(50) {
                        scaleX = 0.96f
                        scaleY = 0.96f
                    }

                    frame(75) {
                        scaleX = 0.94f
                        scaleY = 0.94f
                    }

                    frame(85) {
                        scaleX = 0.92f
                        scaleY = 0.92f
                    }

                    frame(100) {
                        scaleX = 0.9f
                        scaleY = 0.9f
                    }
                }
            }
        },
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddings.extraSmall)
            .background(RecipeAppTheme.colors.white0),
    ) {
        BackgroundLessIconButton(
            onClick = { onNavigateBack() },
            pressedContentColor = RecipeAppTheme.colors.neutral100,
            defaultContentColor = RecipeAppTheme.colors.neutral90,
            modifier = Modifier
                .layoutId(BACK_ARROW_BUTTON_ID)
                .size(RecipeAppTheme.sizes.small)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(
                    R.string.arrow_back_icon
                )
            )
        }

        Text(
            text = stringResource(R.string.create_recipe),
            style = RecipeAppTheme.typography.boldH4,
            color = RecipeAppTheme.colors.neutral100,
            modifier = Modifier
                .layoutId(TITLE_ID)
                .padding(start = RecipeAppTheme.paddings.extraSmall)
        )
    }

//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(RecipeAppTheme.paddings.extraSmall)
//            .background(RecipeAppTheme.colors.white0)
//    ) {
//                BackgroundLessIconButton(
//            onClick = { onNavigateBack() },
//            pressedContentColor = RecipeAppTheme.colors.neutral100,
//            defaultContentColor = RecipeAppTheme.colors.neutral90,
//            modifier = Modifier
//                .layoutId(BACK_ARROW_BUTTON_ID)
//                .size(RecipeAppTheme.sizes.small)
//        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowBack,
//                contentDescription = stringResource(
//                    R.string.arrow_back_icon
//                )
//            )
//        }
//
//        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings))
//
//        Text(
//            text = stringResource(R.string.create_recipe),
//            style = RecipeAppTheme.typography.boldH4,
//            color = RecipeAppTheme.colors.neutral100,
//            modifier = Modifier
//                .layoutId(TITLE_ID)
//                .padding(start = RecipeAppTheme.paddings.extraSmall)
//        )
//    }
}

private const val BACK_ARROW_BUTTON_ID = "back_arrow_button"
private const val TITLE_ID = "title"

@Preview
@Composable
fun RecipeAddingScreenTopBarPreview() {
    RecipeAppTheme {
        RecipeAddingScreenTopBar(0f)
    }
}