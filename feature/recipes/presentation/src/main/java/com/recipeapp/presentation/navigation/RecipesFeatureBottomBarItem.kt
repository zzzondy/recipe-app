package com.recipeapp.presentation.navigation

import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.presentation.R

class RecipesFeatureBottomBarItem : BottomBarItem {
    override val navigationRoute: String = RecipesFeatureScreens.navigationRoute
    override val nameId: Int = R.string.bottom_bar_item_name
    override val iconId: Int = R.drawable.home_icon
}