package com.rabiakambur.cookly.home.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rabiakambur.cookly.home.ui.component.HeaderComponent
import com.rabiakambur.cookly.main.theme.BackgroundColor

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity),
    onRecipeClick: (String) -> Unit
) {
    val state by homeViewModel.state.collectAsState()

    Column {
        HeaderComponent()

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.background(BackgroundColor),
                content = {
                    items(state.recipesList) {
                        RecipeItem(
                            recipesResultResponse = it,
                            onRecipeClick = { recipe ->
                                onRecipeClick.invoke(recipe.recipeId)
                            },
                            onRecipeFavoriteClick = { recipe ->
                                homeViewModel.onFavoriteClick(recipe)
                            }
                        )
                    }
                }
            )

            if (state.isLoading) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Şuanda veriler çekiliyor."
                )
            }

            if (state.isError) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Birader şuanda bir sıkıntı var, acele etme ;)."
                )
            }
        }
    }
}