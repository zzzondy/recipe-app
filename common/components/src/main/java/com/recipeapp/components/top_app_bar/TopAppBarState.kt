package com.recipeapp.components.top_app_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.recipeapp.components.top_app_bar.behaviors.ExitUntilCollapsedState

@Stable
interface TopAppBarState {
    val offset: Float
    val height: Float
    val progress: Float
    val consumed: Float
    var scrollTopLimitReached: Boolean
    var scrollOffset: Float
}

abstract class ScrollFlagState(heightRange: IntRange) : TopAppBarState {

    protected val minHeight = heightRange.first
    protected val maxHeight = heightRange.last
    protected val rangeDifference = maxHeight - minHeight
    protected var _consumed: Float = 0f

    protected abstract var _scrollOffset: Float

    override val height: Float
        get() = (maxHeight - scrollOffset).coerceIn(minHeight.toFloat(), maxHeight.toFloat())

    final override val progress: Float
        get() = (maxHeight - height) / rangeDifference

    final override val consumed: Float
        get() = _consumed

    final override var scrollTopLimitReached: Boolean = true

    init {
        require(heightRange.first >= 0 && heightRange.last >= heightRange.first) {
            "The lowest height value must be >= 0 and the highest height value must be >= the lowest value."
        }
    }
}


@Composable
fun rememberTopAppBarState(
    topAppBarHeightRange: IntRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
): TopAppBarState =
    rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(topAppBarHeightRange)
    }


private val MinToolbarHeight = 66.dp
private val MaxToolbarHeight = 100.dp

