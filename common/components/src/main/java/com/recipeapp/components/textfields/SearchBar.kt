package com.recipeapp.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.recipeapp.components.R
import com.recipeapp.theme.RecipeAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {},
    enabled: Boolean = true,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = RecipeAppTheme.typography.regularSmall.copy(color = RecipeAppTheme.colors.neutral60),
        cursorBrush = SolidColor(RecipeAppTheme.colors.neutral60),
        singleLine = true,
        enabled = enabled,
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Characters,
            imeAction = ImeAction.Search
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .height(RecipeAppTheme.sizes.small)
                    .background(
                        color = if (enabled) RecipeAppTheme.colors.neutral20 else RecipeAppTheme.colors.neutral40,
                        shape = RecipeAppTheme.shapes.medium
                    )
            ) {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    placeholder = {
                        Text(
                            text = placeholder,
                            style = RecipeAppTheme.typography.regularSmall,
                            color = RecipeAppTheme.colors.neutral50
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = stringResource(R.string.search_icon),
                            tint = RecipeAppTheme.colors.neutral50,
                        )
                    },
                    contentPadding = PaddingValues(horizontal = RecipeAppTheme.paddings.small),
                ) {}
            }
        }
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    var value by remember { mutableStateOf("") }

    RecipeAppTheme(isDarkTheme = true) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                value = value,
                onValueChange = { value = it },
                placeholder = "Search",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium)
            )

            SearchBar(
                value = value,
                onValueChange = { value = it },
                placeholder = "Search",
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(RecipeAppTheme.paddings.medium)
            )
        }
    }
}