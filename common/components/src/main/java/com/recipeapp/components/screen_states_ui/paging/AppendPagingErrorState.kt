package com.recipeapp.components.screen_states_ui.paging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.recipeapp.components.R
import com.recipeapp.components.buttons.DefaultButton
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun AppendPagingErrorState(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.some_error),
            color = RecipeAppTheme.colors.neutral100,
            style = RecipeAppTheme.typography.regularLabel,
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))

        DefaultButton(onClick = onRefresh) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}