package com.rabiakambur.cookly.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rabiakambur.cookly.main.navigation.BottomNavigationBar
import com.rabiakambur.cookly.main.navigation.NavigationScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(0.dp)
            ) { BottomNavigationBar(navController = navController) }
        }
    )
    {
        NavigationScreens(navController = navController, modifier = Modifier.padding(it))
    }
}