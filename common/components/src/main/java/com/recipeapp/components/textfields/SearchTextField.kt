package com.recipeapp.components.textfields

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.recipeapp.components.R
import com.recipeapp.components.buttons.BackgroundLessIconButton
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun SearchTextField(
    value: String,
    placeholderText: String,
    onValueChanged: (String) -> Unit,
    onClearInput: () -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DefaultTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier
            .height(80.dp)
            .then(modifier),
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = stringResource(R.string.search_icon)
            )
        },
        trailingIcon = {
            BackgroundLessIconButton(
                onClick = onClearInput
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(
                        R.string.clear_icon
                    )
                )
            }
        },
        placeholder = {
            Text(
                text = placeholderText,
                style = RecipeAppTheme.typography.regularSmall
            )
        },
        keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        textStyle = RecipeAppTheme.typography.regularSmall,
    )
}