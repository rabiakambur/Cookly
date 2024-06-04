package com.rabiakambur.cookly.home.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import coil.compose.AsyncImage
import com.rabiakambur.cookly.model.RecipesResultResponse
import com.rabiakambur.cookly.ui.theme.CardColor

@Composable
fun ItemRecipeList(recipesResultResponse: RecipesResultResponse) {

    Card(
        modifier = Modifier
            .width(220.dp)
            .height(220.dp)
            .padding(all = 7.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(2.dp, Color.Transparent)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(all = 5.dp)
        ) {
            AsyncImage(
                model = recipesResultResponse.recipeImage,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp, 120.dp)
            )
            Text(
                text = recipesResultResponse.recipeTitle,
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}