package com.recipeapp.components.screen_states_ui.snackbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import com.radusalagean.infobarcompose.BaseInfoBarMessage
import com.radusalagean.infobarcompose.InfoBar
import com.radusalagean.infobarcompose.InfoBarSlideEffect
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.UIText

@Composable
fun ErrorSnackBar(
    message: ErrorMessage?,
    onDismiss: () -> Unit = {}
) {
    val content: @Composable (ErrorMessage) -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = RecipeAppTheme.colors.error100,
                    shape = RectangleShape
                )
                .padding(horizontal = RecipeAppTheme.paddings.medium)
        ) {
            Text(
                text = it.title.asString(),
                color = RecipeAppTheme.colors.white0,
                style = RecipeAppTheme.typography.regularH5,
                modifier = Modifier
                    .padding(
                        start = RecipeAppTheme.paddings.medium,
                        top = RecipeAppTheme.paddings.medium
                    ),
            )

            Spacer(modifier = Modifier.height(RecipeAppTheme.paddings.small))

            Text(
                text = it.subtitle.asString(),
                color = RecipeAppTheme.colors.white0,
                style = RecipeAppTheme.typography.regularP,
                modifier = Modifier.padding(
                    start = RecipeAppTheme.paddings.medium,
                    end = RecipeAppTheme.paddings.medium,
                    bottom = RecipeAppTheme.paddings.medium
                ),
                overflow = TextOverflow.Visible,
            )
        }
    }

    InfoBar(
        offeredMessage = message,
        content = content,
        onDismiss = onDismiss,
        shape = RectangleShape,
        fadeEffect = false,
        scaleEffect = false,
        slideEffect = InfoBarSlideEffect.FROM_TOP,
    )
}

data class ErrorMessage(
    val title: UIText,
    val subtitle: UIText,
    override val containsControls: Boolean = false,
    override val backgroundColor: Color? = null,
    override val displayTimeSeconds: Int? = 4,
) : BaseInfoBarMessage()