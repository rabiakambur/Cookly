package com.rabiakambur.cookly.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu

sealed class NavItem {
    object Home : Item(
        path = NavPath.Home.toString(), title = NavTitle.HOME, icon = Icons.Default.Home
    )
    object Favorite : Item(
        path = NavPath.Favorite.toString(), title = NavTitle.FAVORITE, icon = Icons.Default.Favorite
    )
    object Detail : Item(
        path = NavPath.Detail.toString() + "/{id}", title = NavTitle.DETAIL, icon = Icons.Default.Menu
    )

}