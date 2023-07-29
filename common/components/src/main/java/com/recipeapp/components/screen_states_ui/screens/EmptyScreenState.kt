package com.recipeapp.components.screen_states_ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.recipeapp.components.R
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun EmptyScreenState(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.search),
            contentDescription = stringResource(R.string.search_icon),
            colorFilter = ColorFilter.tint(color = RecipeAppTheme.colors.primary50)
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.large))

        Text(
            text = title,
            style = RecipeAppTheme.typography.boldH5,
            color = RecipeAppTheme.colors.neutral100,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = RecipeAppTheme.paddings.medium)
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.extraSmall))

        Text(
            text = subtitle,
            style = RecipeAppTheme.typography.regularP,
            color = RecipeAppTheme.colors.neutral100,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .testTag(ErrorScreenStateTestingTags.SECONDARY_TEXT)
                .padding(horizontal = RecipeAppTheme.paddings.medium)
        )
    }
}