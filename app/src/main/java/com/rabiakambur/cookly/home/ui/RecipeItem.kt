package com.rabiakambur.cookly.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rabiakambur.cookly.R
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResult
import com.rabiakambur.cookly.main.theme.PrimaryColor

@Composable
fun RecipeItem(
    recipesResultResponse: RecipesResult,
    onRecipeClick: (RecipesResult) -> Unit,
    onRecipeFavoriteClick: (RecipesResult) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 7.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(2.dp, Color.Transparent),
        onClick = {
            onRecipeClick.invoke(recipesResultResponse)
        }
    ) {
        Box {
            Column {
                RecipeImage(recipesResultResponse.recipeImage)
                RecipeTitleBackground()
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)

            ) {
                FavoriteIcon(
                    isFavorite = recipesResultResponse.isFavorite ?: false,
                    onFavoriteClick = {
                        onRecipeFavoriteClick.invoke(recipesResultResponse)
                    }
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                RecipeTitle(recipesResultResponse.recipeTitle)
            }
        }
    }
}

@Composable
fun RecipeImage(imageUrl: String) {
    Box {
        AsyncImage(
            model = imageUrl,
            contentDescription = stringResource(R.string.content_description_recipe_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.White),
                        startY = size.height / 2,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient)
                    }
                }
        )
    }
}

@Composable
fun FavoriteIcon(isFavorite: Boolean, onFavoriteClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(end = 10.dp, top = 10.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        IconToggleButton(
            checked = isFavorite,
            onCheckedChange = {
                onFavoriteClick.invoke()
            },
            modifier = Modifier
                .size(34.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                modifier = Modifier
                    .clickable {
                        onFavoriteClick.invoke()
                    },
                tint = PrimaryColor,
                imageVector = if (isFavorite) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = null
            )
        }
    }
}

@Composable
fun RecipeTitleBackground() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(36.dp)
    )
}

@Composable
fun RecipeTitle(recipeTitle: String) {
    Box {
        Text(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, bottom = 16.dp),
            text = recipeTitle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}