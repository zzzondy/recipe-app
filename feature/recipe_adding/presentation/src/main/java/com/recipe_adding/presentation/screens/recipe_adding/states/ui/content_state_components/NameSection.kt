package com.recipe_adding.presentation.screens.recipe_adding.states.ui.content_state_components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.recipe_adding.presentation.R
import com.recipeapp.components.buttons.BackgroundLessIconButton
import com.recipeapp.components.textfields.DefaultTextField

@Composable
internal fun NameSection(
    name: String,
    modifier: Modifier = Modifier,
    onChangedName: (String) -> Unit = {},
    isError: Boolean = false,
) {
    val focusManager = LocalFocusManager.current

    DefaultTextField(
        value = name,
        onValueChange = onChangedName,
        singleLine = true,
        trailingIcon = {
            if (name.isNotEmpty()) {
                BackgroundLessIconButton(
                    onClick = {
                        onChangedName("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close_icon)
                    )
                }
            }
        },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Done
        ),
        placeholder = {
            Text(text = stringResource(R.string.recipe_name))
        },
        modifier = modifier,
        isError = isError,
    )
}