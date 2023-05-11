package com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components

import android.net.Uri
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import com.recipe_adding.presentation.R
import com.recipeapp.components.buttons.BackgroundLessIconButton
import com.recipeapp.components.buttons.DefaultIconButton
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.bounceClick
import com.recipeapp.utils.toPx
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ImageSection(
    images: List<Uri>,
    modifier: Modifier = Modifier,
    onAddImageClicked: () -> Unit = {},
    onRemoveImageClicked: (Uri) -> Unit = {},
    onReplaceImageClicked: (Uri) -> Unit = {},
    isError: Boolean = false,
) {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        DefaultIconButton(
            onClick = onAddImageClicked,
            modifier = Modifier
                .padding(end = RecipeAppTheme.paddings.medium),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(
                    R.string.add_icon
                )
            )
        }

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))

        LazyRow(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            images.forEachIndexed { index, uri ->
                item(key = index) {
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier
                            .padding(horizontal = RecipeAppTheme.paddings.medium)
                            .size(
                                width = RecipeAppTheme.sizes.imageWidth,
                                height = RecipeAppTheme.sizes.imageHeight
                            )
                            .animateItemPlacement(
                                animationSpec = tween(durationMillis = 150)
                            )
                            .bounceClick {
                                onReplaceImageClicked(uri)
                            },
                    ) {
                        CoilImage(
                            imageModel = { uri },
                            modifier = Modifier
                                .size(
                                    width = RecipeAppTheme.sizes.imageWidth,
                                    height = RecipeAppTheme.sizes.imageHeight
                                )
                                .clip(RecipeAppTheme.shapes.medium),
                            loading = {
                                Box(modifier = Modifier.background(RecipeAppTheme.colors.neutral60))
                            },
                            imageOptions = ImageOptions(
                                requestSize = IntSize(
                                    width = RecipeAppTheme.sizes.imageWidth.toPx(),
                                    height = RecipeAppTheme.sizes.imageHeight.toPx()
                                )
                            )
                        )

                        DefaultIconButton(
                            modifier = Modifier.padding(
                                end = RecipeAppTheme.paddings.medium,
                                top = RecipeAppTheme.paddings.medium
                            ),
                            backgroundColor = RecipeAppTheme.colors.white0,
                            contentColor = RecipeAppTheme.colors.primary50,
                            shape = CircleShape,
                            onClick = { onRemoveImageClicked(uri) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = stringResource(
                                    R.string.delete_icon
                                )
                            )
                        }
                    }
                }
            }

            if (images.isEmpty()) {
                item(key = "placeholder") {
                    Box(
                        modifier = Modifier
                            .size(
                                width = RecipeAppTheme.sizes.imageWidth,
                                height = RecipeAppTheme.sizes.imageHeight
                            )
                            .bounceClick {
                                onAddImageClicked()
                            }
                            .background(
                                color = if (isError) RecipeAppTheme.colors.error100 else RecipeAppTheme.colors.neutral20,
                                shape = RecipeAppTheme.shapes.medium
                            )
                            .padding(horizontal = RecipeAppTheme.paddings.medium),
                        contentAlignment = Alignment.Center
                    ) {
                        BackgroundLessIconButton(
                            onClick = onAddImageClicked,
                            size = RecipeAppTheme.sizes.medium,
                            defaultContentColor = if (isError) RecipeAppTheme.colors.neutral100 else RecipeAppTheme.colors.primary50,
                            pressedContentColor = if (isError) RecipeAppTheme.colors.neutral80 else RecipeAppTheme.colors.primary70,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(
                                    R.string.add_icon
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}