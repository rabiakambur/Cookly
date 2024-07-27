package com.rabiakambur.cookly.home.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rabiakambur.cookly.R
import com.rabiakambur.cookly.home.ui.component.HeaderComponent
import com.rabiakambur.cookly.main.theme.BackgroundColor

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity),
    onRecipeClick: (String) -> Unit
) {
    val state by homeViewModel.state.collectAsState()

    Column {
        HeaderComponent(
            onSearchClicked = {
                homeViewModel.searchRecipes(it)
            },
            onClear = {
                homeViewModel.searchRecipes("")
            }
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.background(BackgroundColor),
                content = {
                    items(state.filteredRecipesList?.mapNotNull { it }.orEmpty()) {
                        RecipeItem(
                            recipesResultResponse = it,
                            onRecipeClick = { recipe ->
                                onRecipeClick.invoke(recipe.recipeId.orEmpty())
                            },
                            onRecipeFavoriteClick = { recipe ->
                                homeViewModel.onFavoriteClick(recipe)
                            }
                        )
                    }
                }
            )
            if (state.isLoading) {
                LoadingComponent()
            }

            if (state.isError) {
                ErrorComponent()
            }
        }
    }
}

@Composable
fun LoadingComponent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_loading_foreground),
            contentDescription = stringResource(R.string.loading),
            modifier = Modifier
                .size(200.dp)
        )
    }
}

@Composable
fun ErrorComponent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_error_foreground),
                    contentDescription = stringResource(R.string.content_description_error),
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.Center)
                )
                Text(
                    text = stringResource(R.string.error),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(top = 2.dp, bottom = 40.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }
    }
}