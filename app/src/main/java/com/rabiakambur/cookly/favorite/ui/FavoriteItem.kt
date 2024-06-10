package com.rabiakambur.cookly.favorite.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rabiakambur.cookly.favorite.data.source.local.RecipeEntity
import com.rabiakambur.cookly.main.theme.CardColor

@Composable
fun FavoriteItem(
    favoriteRecipe: RecipeEntity,
    onDeleteClick: (RecipeEntity) -> Unit
) {
    var shouldShowItemDeletionDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(2.dp, Color.Transparent),
        onClick = {

        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 10.dp)
        ) {
            AsyncImage(
                model = favoriteRecipe.recipeImage,
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp, 250.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "${favoriteRecipe.recipeTitle} Ingredients",
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    text = favoriteRecipe.recipeIngredients,
                    textAlign = TextAlign.Justify,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    text = "How to Make ${favoriteRecipe.recipeTitle}",
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    text = favoriteRecipe.recipeStep,
                    textAlign = TextAlign.Justify,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }

            Icon(
                tint = Color.Black,
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.End)
                    .clickable {
                        shouldShowItemDeletionDialog = true
                    }
            )
            if (shouldShowItemDeletionDialog) {
                FavoriteItemDeletionDialog({
                    shouldShowItemDeletionDialog = it
                }, {
                    onDeleteClick(favoriteRecipe)
                })
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavoriteItemPreview() {
    FavoriteItem(
        favoriteRecipe = RecipeEntity(
            uid = 1,
            recipeTitle = "Asparagus and Pea Soup: Real Convenience Food",
            recipeImage = "https://img.spoonacular.com/recipes/716406-312x231.jpg",
            readyInMinutes = 5,
            recipeServings = 8,
            dishTypes = "meal",
            recipeStep = "Add peas (the heat of the soup will quickly thaw them) and puree until smooth; add more until it reaches the thickness you like.Top with chives and a small dollop of creme fraiche or sour cream or greek yogurt.",
            recipeIngredients = "salt and pepper, asparagus, broth, red pepper flakes",
            isFavorite = true
        ),
        onDeleteClick = {

        })
}