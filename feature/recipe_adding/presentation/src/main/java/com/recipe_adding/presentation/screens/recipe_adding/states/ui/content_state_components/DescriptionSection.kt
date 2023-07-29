package com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.recipe_adding.presentation.R
import com.recipeapp.components.textfields.DefaultTextField
import com.recipeapp.theme.RecipeAppTheme

private const val DESCRIPTION_MAX_LINES = 15

@Composable
internal fun DescriptionSection(
    description: String,
    modifier: Modifier = Modifier,
    onDescriptionChanged: (String) -> Unit = {},
    isError: Boolean = false,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.description),
            color = RecipeAppTheme.colors.neutral100,
            style = RecipeAppTheme.typography.boldH5
        )

        Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))

        DefaultTextField(
            value = description,
            onValueChange = { onDescriptionChanged(it) },
            modifier = Modifier
                .testTag(DescriptionSectionTestingTags.DESCRIPTION_TEXT_FIELD_TAG)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            maxLines = DESCRIPTION_MAX_LINES,
            placeholder = {
                Text(text = stringResource(R.string.description))
            },
            isError = isError,
        )
    }
}

object DescriptionSectionTestingTags {
    const val DESCRIPTION_TEXT_FIELD_TAG = "DESCRIPTION_TEXT_FIELD_TAG"
}