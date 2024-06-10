package com.rabiakambur.cookly.favorite.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabiakambur.cookly.favorite.data.source.local.RecipeEntity
import com.rabiakambur.cookly.main.theme.BackgroundColor

@Composable
fun FavoriteItem(
    favoriteRecipe: RecipeEntity,
    onDeleteClick: (RecipeEntity) -> Unit
) {
    var shouldShowItemDeletionDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundColor
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(2.dp, Color.Transparent),
        onClick = {

        }
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row {
                Text(
                    text = "sdfsdfsdfsdfsdfsdfsdfsdflksflsdkşflsdkfşlksdşlfkşsdlkfşl",
                    textAlign = TextAlign.Justify,
                    fontSize = 13.sp,
                    color = Color.Black
                )
                Icon(
                    tint = Color.Black,
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    modifier = Modifier
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavoriteItemPreview() {
    FavoriteItem(
        favoriteRecipe = RecipeEntity(uid = 1, name = "", image = "", isFavorite = true),
        onDeleteClick = {

        })
}