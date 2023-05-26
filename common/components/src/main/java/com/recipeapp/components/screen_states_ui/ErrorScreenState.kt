package com.recipeapp.components.screen_states_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.recipeapp.components.R
import com.recipeapp.components.buttons.DefaultButton
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun ErrorScreenState(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onTryAgainButtonClicked: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.error_icon),
            contentDescription = stringResource(R.string.error_icon),
            colorFilter = ColorFilter.tint(color = RecipeAppTheme.colors.error100),
            modifier = Modifier.size(RecipeAppTheme.sizes.extraLarge)
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.large))

        Text(
            text = title,
            style = RecipeAppTheme.typography.boldH5,
            color = RecipeAppTheme.colors.neutral100,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = RecipeAppTheme.paddings.medium)
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.extraSmall))

        Text(
            text = subtitle,
            style = RecipeAppTheme.typography.regularP,
            color = RecipeAppTheme.colors.neutral100,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = RecipeAppTheme.paddings.medium)
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.medium))

        DefaultButton(
            onClick = onTryAgainButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = RecipeAppTheme.paddings.large)
        ) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}


@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ErrorScreenStatePreview() {
    RecipeAppTheme(isDarkTheme = true) {
        ErrorScreenState(
            title = "Ошибка во время обработки запроса",
            subtitle = "Произошла ошибка, попробуйте позже еще раз",
            modifier = Modifier.fillMaxSize()
        )
    }
}