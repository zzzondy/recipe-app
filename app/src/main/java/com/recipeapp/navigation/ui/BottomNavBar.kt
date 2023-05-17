package com.recipeapp.navigation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.recipeapp.R
import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.theme.RecipeAppTheme
import com.recipeapp.utils.bounceClick

@Composable
fun BottomNavBar(
    bottomBarItems: List<BottomBarItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    middleButton: @Composable RowScope.() -> Unit = {}
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationParentRoute = navBackStackEntry?.destination?.parent?.route

    val shouldShowBottomBar =
        bottomBarItems.any { it.navigationRoute == currentDestinationParentRoute } || currentDestinationParentRoute == null

    AnimatedVisibility(
        visible = shouldShowBottomBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomAppBar(
            backgroundColor = RecipeAppTheme.colors.white0,
            modifier = modifier
        ) {
            bottomBarItems.forEachIndexed { index, bottomBarItem ->
                val selected = currentDestinationParentRoute == bottomBarItem.navigationRoute

                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(bottomBarItem.navigationRoute) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(bottomBarItem.iconId),
                            contentDescription = stringResource(R.string.bottom_bar_icon),
                            modifier = Modifier
                                .size(30.dp)
                                .bounceClick()
                        )
                    },
                    selectedContentColor = RecipeAppTheme.colors.primary50,
                    interactionSource = remember { MutableInteractionSource() },

                )

                if (checkIndexIsMiddleOfList(index, bottomBarItems)) {
                    middleButton()
                }
            }
        }
    }
}

private fun checkIndexIsMiddleOfList(index: Int, list: List<*>): Boolean {
    return if (list.size == 1) {
        true
    } else if (list.size % 2 == 0 && index + 1 == list.size / 2) {
        true
    } else list.size % 2 != 0 && index + 1 == list.size / 2
}