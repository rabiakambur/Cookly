package com.rabiakambur.cookly.main.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rabiakambur.cookly.detail.ui.DetailScreen
import com.rabiakambur.cookly.favorite.ui.FavoriteScreen
import com.rabiakambur.cookly.home.ui.HomeScreen

@Composable
fun NavigationScreens(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navActions: NavigationActions = remember(navController){
        NavigationActions(navController)
    }
) {
    NavHost(navController, startDestination = NavItem.Home.path, modifier.padding()) {
        composable(
            NavItem.Home.path
        ) {
            HomeScreen() {
                navActions.navigateToDetail(it)
        } }

        composable(
            NavItem.Favorite.path
        ) {
            FavoriteScreen() }

        composable(
            NavItem.Detail.path
        ) { arguments ->
            val id = arguments.arguments?.getString("id") ?: "0"
            DetailScreen(id) }
    }
}
