package com.recipeapp.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    isError: Boolean = false,
    readOnly: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = RecipeAppTheme.shapes.default,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = if (enabled) Color.Transparent else RecipeAppTheme.colors.neutral10,
            textColor = RecipeAppTheme.colors.neutral90,
            disabledTextColor = RecipeAppTheme.colors.neutral30,
            cursorColor = RecipeAppTheme.colors.neutral90,
            unfocusedBorderColor = RecipeAppTheme.colors.neutral20,
            focusedBorderColor = RecipeAppTheme.colors.primary50,
            disabledBorderColor = RecipeAppTheme.colors.neutral30,
            leadingIconColor = RecipeAppTheme.colors.neutral20,
            disabledLeadingIconColor = RecipeAppTheme.colors.neutral20,
            trailingIconColor = RecipeAppTheme.colors.neutral20,
            disabledTrailingIconColor = RecipeAppTheme.colors.neutral20,
            placeholderColor = RecipeAppTheme.colors.neutral30,
            disabledPlaceholderColor = RecipeAppTheme.colors.neutral30,
            errorCursorColor = RecipeAppTheme.colors.error100,
            errorBorderColor = RecipeAppTheme.colors.error100,
            errorLabelColor = RecipeAppTheme.colors.error100,
            errorLeadingIconColor = RecipeAppTheme.colors.error100,
            errorTrailingIconColor = RecipeAppTheme.colors.error100
        ),
        textStyle = RecipeAppTheme.typography.regularLabel,
        isError = isError,
        readOnly = readOnly
    )
}


@Preview
@Composable
private fun DefaultTextFieldPreview() {
    var text by remember { mutableStateOf("") }
    RecipeAppTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            DefaultTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium),
                placeholder = {
                    Text(text = "Placeholder")
                }
            )

            DefaultTextField(
                enabled = false,
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    IconButton(onClick = { text = "" }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                },
                placeholder = {
                    Text(text = "Placeholder")
                }
            )

            DefaultTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium),
                placeholder = {
                    Text(text = "Placeholder")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        IconButton(onClick = { text = "" }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                        }
                    }
                }
            )
        }
    }
}