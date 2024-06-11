package com.rabiakambur.cookly.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResult
import com.rabiakambur.cookly.home.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            homeRepository
                .getAllRecipe()
                .onEach {
                    _recipesListFlow.value = it.results
                }
                .launchIn(viewModelScope)
        }
    }

    fun addRecipeToFavorite(recipesResult: RecipesResult) {
        viewModelScope.launch {
            homeRepository.addRecipeToFavorite(favoriteRecipeEntity = FavoriteRecipeEntity(
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
}