package com.rabiakambur.cookly.favorite.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rabiakambur.cookly.favorite.data.source.local.RecipeEntity
import com.rabiakambur.cookly.main.theme.BackgroundColor

@Composable
fun FavoriteItem(
    favoriteRecipe: RecipeEntity,
    onDeleteClick: (RecipeEntity) -> Unit
) {
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
            Text(
                text = "sdfsdfsdfsdfsdfsdfsdfsdflksflsdkşflsdkfşlksdşlfkşsdlkfşl",
                textAlign = TextAlign.Justify,
                fontSize = 13.sp,
                color = Color.Black
            )
        }
    }
}