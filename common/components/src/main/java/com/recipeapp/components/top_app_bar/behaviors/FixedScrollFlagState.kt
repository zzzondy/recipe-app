package com.recipeapp.components.top_app_bar.behaviors

import com.recipeapp.components.top_app_bar.ScrollFlagState

abstract class FixedScrollFlagState(heightRange: IntRange) : ScrollFlagState(heightRange) {

    final override val offset: Float = 0f
}