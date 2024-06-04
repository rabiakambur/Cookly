package com.rabiakambur.cookly.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rabiakambur.cookly.favorite.ui.FavoriteScreen
import com.rabiakambur.cookly.home.ui.HomeScreen
import com.rabiakambur.cookly.navigation.NavItem

@Composable
fun NavigationScreens(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = NavItem.Home.path, modifier.padding()) {
        composable(NavItem.Home.path) { HomeScreen() }
        composable(NavItem.Favorite.path) { FavoriteScreen() }
    }
}
