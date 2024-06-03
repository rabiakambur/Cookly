package com.rabiakambur.cookly.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rabiakambur.cookly.favorite.ui.FavoriteScreen
import com.rabiakambur.cookly.home.ui.HomeScreen
import com.rabiakambur.cookly.navigation.NavItem

@Composable
fun NavigationScreens(navController: NavHostController) {
    NavHost(navController, startDestination = NavItem.Home.path) {
        composable(NavItem.Home.path) { HomeScreen() }
        composable(NavItem.Favorite.path) { FavoriteScreen() }
    }
}
