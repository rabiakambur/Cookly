package com.rabiakambur.cookly.main.navigation

import androidx.navigation.NavHostController

class NavigationActions(private val navController: NavHostController) {

    fun navigateToDetail(id: String) {
        navController.navigate(NavItem.Detail.path.replace("{id}", id)) {

        }
    }
}