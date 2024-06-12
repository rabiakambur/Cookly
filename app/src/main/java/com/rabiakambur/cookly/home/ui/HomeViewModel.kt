package com.rabiakambur.cookly.home.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabiakambur.cookly.detail.ui.DetailState
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResult
import com.rabiakambur.cookly.home.data.repository.HomeRepository
import com.rabiakambur.cookly.main.util.Async
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
    private val homeRepository: HomeRepository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(
        HomeState()
    )
    val state: StateFlow<HomeState> = _state

    private val _detailState: MutableStateFlow<DetailState> = MutableStateFlow(
        DetailState(null)
    )
    val detailState: StateFlow<DetailState> = _detailState

    init {
        fetchRecipes()
        observeFavorites()
    }

    fun getRecipeById(id: String) {
        val recipe = state.value.recipesList.find { it.recipeId == id }
        _detailState.value = DetailState(recipesResult = recipe)
    }

    private fun fetchRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository
                .getAllRecipe()
                .onEach {
                    when (it) {
                        is Async.Success -> {
                            _state.value = HomeState(
                                recipesList = it.data.results,
                                isLoading = false,
                                isError = false
                            )
                        }

                        is Async.Loading -> {
                            _state.value = HomeState(
                                isLoading = true,
                                isError = false
                            )
                        }

                        else -> {
                            _state.value = HomeState(
                                isLoading = false,
                                isError = true
                            )
                        }
                    }
                }
                .launchIn(this)
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            homeRepository
                .getFavoriteRecipes()
                .onEach { favoriteRecipes ->
                    val titles = favoriteRecipes.map { it.recipeTitle }
                    val updatedRecipesList = _state.value.recipesList.map {
                        it.copy(isFavorite = titles.contains(it.recipeTitle))
                    }
                    _state.value = _state.value.copy(recipesList = updatedRecipesList)
                }
                .launchIn(this)
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