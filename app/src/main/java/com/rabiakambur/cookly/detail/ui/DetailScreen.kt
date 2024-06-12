package com.rabiakambur.cookly.detail.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rabiakambur.cookly.R
import com.rabiakambur.cookly.home.ui.HomeViewModel
import com.rabiakambur.cookly.main.theme.BackgroundColor
import com.rabiakambur.cookly.main.theme.PrimaryColor

@Composable
fun DetailScreen(
    recipeId: String,
    homeViewModel: HomeViewModel = hiltViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity),
) {

    val state by homeViewModel.detailState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getRecipeById(id = recipeId)
    }

    Scaffold(
        topBar = {
            DetailTopBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .background(BackgroundColor)
        ) {
            DetailItem(state = state)
        }
    }
}

@Composable
fun DetailTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(PrimaryColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.recipe_detail),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}