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
import androidx.compose.ui.text.TextStyle
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

        ReusableText(
            text = "${state.recipesResult?.recipeTitle}",
            fontSize = 24,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )

        ReusableText(
            text = stringResource(R.string.recipe_ingredients),
            fontSize = 24,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
        )

        ReusableText(
            text = state.getRecipeIngredientsAsFormatted(),
            fontSize = 16,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.Start)
        )

        ReusableText(
            text = stringResource(R.string.recipe_preparation),
            fontSize = 24,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .align(Alignment.Start)

        )

        ReusableText(
            text = state.getRecipeStepsAsFormatted(),
            fontSize = 16,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .align(Alignment.Start)
        )

    }
}

@Composable
fun ReusableText(
    text: String,
    fontSize: Int,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Justify
) {
    Text(
        text = text,
        textAlign = textAlign,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        style = style,
        color = color,
        modifier = modifier
            .padding(10.dp)
    )
}