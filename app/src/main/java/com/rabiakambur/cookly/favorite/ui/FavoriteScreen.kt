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
import com.rabiakambur.cookly.favorite.data.source.local.RecipeEntity
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
    favoriteList: List<RecipeEntity>,
    onDeleteClick: (RecipeEntity) -> Unit
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
            RecipeEntity(
                uid = 1,
                recipeTitle = "Asparagus and Pea Soup: Real Convenience Food",
                recipeImage = "https://img.spoonacular.com/recipes/716406-312x231.jpg",
                readyInMinutes = 5,
                recipeServings = 8,
                dishTypes = "meal",
                recipeStep = "Add peas (the heat of the soup will quickly thaw them) and puree until smooth; add more until it reaches the thickness you like.Top with chives and a small dollop of creme fraiche or sour cream or greek yogurt.",
                recipeIngredients = "salt and pepper, asparagus, broth, red pepper flakes",
                isFavorite = true
            )
        )
    ) {

    }
}