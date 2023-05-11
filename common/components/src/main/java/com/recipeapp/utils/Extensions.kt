package com.recipeapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import kotlin.math.roundToInt

@Composable
fun Dp.toPx(): Int = with(LocalDensity.current) { this@toPx.toPx() }.roundToInt()