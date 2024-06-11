package com.rabiakambur.cookly.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResult
import com.rabiakambur.cookly.home.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _recipesListFlow: MutableStateFlow<List<RecipesResult>> = MutableStateFlow(
        listOf()
    )
    val recipesListFlow: StateFlow<List<RecipesResult>> = _recipesListFlow

    init {
        fetchRecipes()
        observeFavorites()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                homeRepository
                    .getAllRecipe()
                    .collect {
                        _recipesListFlow.value = it.results
                    }
            }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            homeRepository
                .getFavoriteRecipes()
                .onEach { favoriteRecipes ->
                    val titles = favoriteRecipes.map { it.recipeTitle }
                    _recipesListFlow.value = _recipesListFlow.value.map {
                        it.copy(isFavorite = titles.contains(it.recipeTitle))
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun onFavoriteClick(recipesResult: RecipesResult) {
        viewModelScope.launch {
            if (recipesResult.isFavorite == true) {
                removeRecipeFromFavorite(recipesResult)
            } else {
                addRecipeToFavorite(recipesResult)
            }
        }
    }

    private fun addRecipeToFavorite(recipesResult: RecipesResult) {
        viewModelScope.launch {
            homeRepository.addRecipeToFavorite(
                favoriteRecipeEntity = FavoriteRecipeEntity(
                    uid = 0,
                    recipeImage = recipesResult.recipeImage,
                    recipeTitle = recipesResult.recipeTitle,
                    readyInMinutes = recipesResult.recipeReadyInMinutes,
                    recipeServings = recipesResult.recipeServings,
                    dishTypes = recipesResult.dishTypes,
                    instructions = recipesResult.analyzedInstructions
                )
            )
        }
    }

    private fun removeRecipeFromFavorite(recipesResult: RecipesResult) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                homeRepository
                    .deleteRecipeByTitle(recipesResult.recipeTitle)
            }
        }
    }
}