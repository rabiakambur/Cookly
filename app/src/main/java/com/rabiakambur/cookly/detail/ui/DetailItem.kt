package com.rabiakambur.cookly.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun DetailItem(
    state: DetailState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = state.recipesResult?.recipeImage,
            contentDescription = stringResource(R.string.content_description_recipe_image),
            modifier = Modifier
                .size(300.dp, 300.dp)
        )
        Text(
            text = "${state.recipesResult?.recipeTitle}",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .padding(10.dp)
        )

        Text(
            text = stringResource(R.string.recipe_ingredients),
            textAlign = TextAlign.Start,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Start)
        )
        Text(
            text = state.getRecipeIngredientsAsFormatted(),
            textAlign = TextAlign.Justify,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Start)
        )
        Text(
            text = stringResource(R.string.recipe_preparation),
            textAlign = TextAlign.Justify,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Start)
        )
        Text(
            text = state.getRecipeStepsAsFormatted(),
            textAlign = TextAlign.Justify,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Start)
        )
    }
}