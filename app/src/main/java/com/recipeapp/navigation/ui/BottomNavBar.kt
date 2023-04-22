package com.recipeapp.navigation.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.recipeapp.R
import com.recipeapp.navigation.BottomBarItem
import com.recipeapp.theme.RecipeAppTheme

@Composable
fun BottomNavBar(
    bottomBarItems: List<BottomBarItem>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationParentRoute = navBackStackEntry?.destination?.parent?.route

    BottomAppBar(
        backgroundColor = RecipeAppTheme.colors.white0,
        cutoutShape = CircleShape,
        modifier = modifier
    ) {
        bottomBarItems.forEach { bottomBarItem ->
            val selected = currentDestinationParentRoute == bottomBarItem.navigationRoute

            BottomNavigationItem(
                selected = selected,
                onClick = {
                    navController.navigate(bottomBarItem.navigationRoute) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(bottomBarItem.iconId),
                        contentDescription = stringResource(R.string.bottom_bar_icon)
                    )
                },
                selectedContentColor = RecipeAppTheme.colors.primary50,
                interactionSource = remember { MutableInteractionSource() }
            )
        }
    }
}