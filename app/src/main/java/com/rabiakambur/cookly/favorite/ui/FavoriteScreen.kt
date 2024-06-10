package com.rabiakambur.cookly.favorite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.main.theme.BackgroundColor

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by favoriteViewModel.state.collectAsState()

    FavoriteRecipesList(state.favoriteList) { recipe ->
        favoriteViewModel.deleteFavoriteRecipe(recipe)
    }
}

@Composable
fun FavoriteRecipesList(
    favoriteList: List<FavoriteRecipeEntity>,
    onDeleteClick: (FavoriteRecipeEntity) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        items(favoriteList) { recipe ->
            ExpandableCard(
                favoriteRecipe = recipe,
                onDeleteClick = {
                    onDeleteClick.invoke(recipe)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavoriteScreenPreview() {
    FavoriteRecipesList(
        favoriteList = listOf(
            FavoriteRecipeEntity(
                uid = 1,
                recipeTitle = "Asparagus and Pea Soup: Real Convenience Food",
                recipeImage = "https://img.spoonacular.com/recipes/716406-312x231.jpg",
                readyInMinutes = 5,
                recipeServings = 8,
                dishTypes = listOf("Meal"),
                instructions = listOf()
            )
        )
    ) {

    }
}