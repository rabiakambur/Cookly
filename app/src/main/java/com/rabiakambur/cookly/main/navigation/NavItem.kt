package com.rabiakambur.cookly.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

sealed class NavItem {
    object Home : Item(
        path = NavPath.Home.toString(), title = NavTitle.HOME, icon = Icons.Default.Home
    )
    object Favorite : Item(
        path = NavPath.Favorite.toString(), title = NavTitle.FAVORITE, icon = Icons.Default.Favorite
    )
}