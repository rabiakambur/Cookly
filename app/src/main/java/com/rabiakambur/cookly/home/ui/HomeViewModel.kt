package com.rabiakambur.cookly.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabiakambur.cookly.detail.ui.DetailState
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.home.data.repository.HomeRepository
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResult
import com.rabiakambur.cookly.main.util.Async
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(
        HomeState()
    )
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _detailState: MutableStateFlow<DetailState> = MutableStateFlow(
        DetailState(null)
    )
    val detailState: StateFlow<DetailState> = _detailState

    init {
        fetchRecipes()
        observeFavorites()
    }

    private fun fetchRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository
                .getAllRecipe()
                .onEach {
                    when (it) {
                        is Async.Success -> {
                            _state.update { state ->
                                state.copy(
                                    recipesList = it.data.results,
                                    filteredRecipesList = it.data.results,
                                    isLoading = false,
                                    isError = false
                                )
                            }
                        }

                        is Async.Loading -> {
                            _state.update { state ->
                                state.copy(
                                    isLoading = true,
                                    isError = false
                                )
                            }
                        }

                        else -> {
                            _state.update { state ->
                                state.copy(
                                    isLoading = false,
                                    isError = true
                                )
                            }
                        }
                    }
                }
                .launchIn(this)
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository
                .getFavoriteRecipes()
                .onEach { favoriteRecipes ->
                    val titles = favoriteRecipes.map { it.recipeTitle }
                    val updatedRecipesList = state.value.recipesList?.map {
                        it?.copy(isFavorite = titles.contains(it.recipeTitle))
                    }
                    _state.update { state ->
                        state.copy(recipesList = updatedRecipesList)
                    }
                }
                .launchIn(this)
        }
    }

    fun getRecipeById(id: String) {
        val recipe = state.value.recipesList?.find { it?.recipeId == id }
        _detailState.update { state ->
            state.copy(recipesResult = recipe)
        }
    }

    fun onFavoriteClick(recipesResult: RecipesResult) {
        viewModelScope.launch(Dispatchers.IO) {
            if (recipesResult.isFavorite == true) {
                removeRecipeFromFavorite(recipesResult)
            } else {
                addRecipeToFavorite(recipesResult)
            }
        }
    }

    private fun addRecipeToFavorite(recipesResult: RecipesResult) {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.addRecipeToFavorite(
                favoriteRecipeEntity = FavoriteRecipeEntity(
                    uid = 0,
                    recipeImage = recipesResult.recipeImage.orEmpty(),
                    recipeTitle = recipesResult.recipeTitle.orEmpty(),
                    readyInMinutes = recipesResult.recipeReadyInMinutes ?: 0,
                    recipeServings = recipesResult.recipeServings ?: 0,
                    dishTypes = recipesResult.dishTypes?.filterNotNull().orEmpty(),
                    instructions = recipesResult.analyzedInstructions?.filterNotNull().orEmpty()
                )
            )
        }
    }

    private fun removeRecipeFromFavorite(recipesResult: RecipesResult) {
        viewModelScope.launch(Dispatchers.IO) {
            recipesResult.recipeTitle?.let {
                homeRepository
                    .deleteRecipeByTitle(it)
            }
        }
    }

    fun searchRecipes(query: String) {
        val filteredRecipes = state.value.recipesList?.filter {
            it?.recipeTitle?.contains(query, ignoreCase = true) == true
        }
        _state.update { state ->
            state.copy(
                filteredRecipesList = filteredRecipes
            )
        }
    }
}