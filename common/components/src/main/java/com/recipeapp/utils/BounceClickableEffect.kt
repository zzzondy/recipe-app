package com.recipeapp.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

enum class ClickState {
    PRESSED, IDLE
}

fun Modifier.bounceClick(enabled: Boolean = true, otherEffect: (ClickState) -> Unit = {}) = composed {

    var buttonState by remember { mutableStateOf(ClickState.IDLE) }

    val scale by animateFloatAsState(if (buttonState == ClickState.PRESSED) 0.95f else 1f)

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            enabled = enabled,
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { }
        )
        .then(
            if (enabled) {
                Modifier.pointerInput(buttonState) {
                    awaitPointerEventScope {
                        buttonState = if (buttonState == ClickState.PRESSED) {
                            waitForUpOrCancellation()
                            ClickState.IDLE
                        } else {
                            awaitFirstDown(false)
                            ClickState.PRESSED
                        }
                        otherEffect(buttonState)
                    }
                }
            } else {
                Modifier
            }
        )

}