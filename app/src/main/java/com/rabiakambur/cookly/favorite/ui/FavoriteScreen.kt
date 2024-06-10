package com.rabiakambur.cookly.favorite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rabiakambur.cookly.main.theme.BackgroundColor

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by favoriteViewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        items(state.favoriteList) { recipe ->
            FavoriteItem(
                favoriteRecipe = recipe,
                onDeleteClick = {
                    favoriteViewModel.deleteFavoriteRecipe(recipe)
                }
            )
        }
    }
}