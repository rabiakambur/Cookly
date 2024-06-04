package com.rabiakambur.cookly.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rabiakambur.cookly.main.navigation.BottomNavigationBar
import com.rabiakambur.cookly.main.navigation.NavigationScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomAppBar { BottomNavigationBar(navController = navController) }
        }
    )
    {
        NavigationScreens(navController = navController, modifier = Modifier.padding(it))
    }
}