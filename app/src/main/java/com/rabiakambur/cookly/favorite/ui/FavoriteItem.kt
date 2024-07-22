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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rabiakambur.cookly.R
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.main.theme.CardColor

@Composable
fun FavoriteItem(
    state: FavoriteItemState,
    onDeleteClick: (FavoriteRecipeEntity) -> Unit
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
                model = state.getRecipeImage(),
                contentDescription = stringResource(R.string.content_description_recipe_image),
                modifier = Modifier
                    .size(250.dp, 250.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ReusableText(
                    text = stringResource(R.string.recipe_ingredients),
                    fontSize = 14,
                    fontWeight = FontWeight.Bold
                )
                ReusableText(
                    text = state.getRecipeIngredientsAsFormatted(),
                    fontSize = 14,
                    fontWeight = FontWeight.Normal
                )
                ReusableText(
                    text = stringResource(R.string.recipe_preparation),
                    fontSize = 16,
                    fontWeight = FontWeight.Bold
                )
                ReusableText(
                    text = state.getRecipeStepsAsFormatted(),
                    fontSize = 14,
                    fontWeight = FontWeight.Normal
                )

            }

            Icon(
                tint = Color.Black,
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.content_description_delete_icon),
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
                    onDeleteClick(state.favoriteRecipe)
                })
            }
        }
    }
}

@Composable
fun ReusableText(
    text: String,
    fontSize: Int,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Justify
) {
    Text(
        text = text,
        textAlign = textAlign,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        color = color,
        modifier = modifier
            .padding(10.dp)
    )
}