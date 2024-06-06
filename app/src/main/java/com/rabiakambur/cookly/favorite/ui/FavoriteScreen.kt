package com.rabiakambur.cookly.favorite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rabiakambur.cookly.main.theme.BackgroundColor

@Composable
fun FavoriteScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        items(3) {
            FavoriteItem()
        }
    }
}