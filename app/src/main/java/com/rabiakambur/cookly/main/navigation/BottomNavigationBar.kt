package com.rabiakambur.cookly.main.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.rabiakambur.cookly.main.theme.CardColor

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navItems = listOf(NavItem.Home, NavItem.Favorite)
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.Black
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.icon, contentDescription = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    indicatorColor = CardColor
                ),
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.path) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}