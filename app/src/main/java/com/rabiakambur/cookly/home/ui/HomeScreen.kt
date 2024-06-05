package com.rabiakambur.cookly.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rabiakambur.cookly.home.ui.component.HeaderComponent
import com.rabiakambur.cookly.main.theme.BackgroundColor

@Composable
fun HomeScreen() {

    val viewModel: HomeViewModel = viewModel()

    val data by viewModel.recipesListFlow.collectAsState()

    Column {
        HeaderComponent()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.background(BackgroundColor),
            content = {
                items(data) {
                    RecipeItem(it)
                }
            }
        )
    }
}