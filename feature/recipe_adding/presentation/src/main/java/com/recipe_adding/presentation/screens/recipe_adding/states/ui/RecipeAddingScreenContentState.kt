package com.recipe_adding.presentation.screens.recipe_adding.states.ui

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.recipe_adding.presentation.R
import com.recipeapp.components.buttons.BackgroundLessIconButton
import com.recipeapp.theme.RecipeAppTheme
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun RecipeAddingScreenContentState(
    images: List<Uri>,
    modifier: Modifier = Modifier,
    onAddImageClicked: () -> Unit = {}

) {
    LazyColumn(modifier = modifier) {
        item {
            ImageSection(
                images = images,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(RecipeAppTheme.sizes.imageHeight),
                onAddImageClicked = onAddImageClicked
            )
        }
    }
}


@Composable
private fun ImageSection(
    images: List<Uri>,
    modifier: Modifier = Modifier,
    onAddImageClicked: () -> Unit = {}
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        item {
            Spacer(modifier = Modifier.width(RecipeAppTheme.paddings.small))
        }

        images.forEach { uri ->
            item {
                CoilImage(
                    imageModel = { uri },
                    modifier = Modifier
                        .padding(end = RecipeAppTheme.paddings.medium)
                        .size(
                            width = RecipeAppTheme.sizes.imageWidth,
                            height = RecipeAppTheme.sizes.imageHeight
                        )
                        .clip(RecipeAppTheme.shapes.medium)
                )
            }
        }

        item {
            BackgroundLessIconButton(
                onClick = onAddImageClicked,
                modifier = Modifier
                    .size(RecipeAppTheme.sizes.extraLarge)
                    .padding(end = RecipeAppTheme.paddings.medium)
            ) {
                Icon(
                    painter = painterResource(R.drawable.add_circle),
                    contentDescription = stringResource(
                        R.string.add_circle_icon
                    ),
                    modifier = Modifier
                        .size(RecipeAppTheme.sizes.extraLarge)
                )
            }
        }
    }
}