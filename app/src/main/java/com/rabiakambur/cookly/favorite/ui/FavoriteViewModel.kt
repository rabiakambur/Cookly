package com.rabiakambur.cookly.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.favorite.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state

    init {
        fetchAllFavorites()
    }

    private fun fetchAllFavorites() {
        viewModelScope.launch {
            repository.getFavoriteRecipes().onEach {
                _state.value = FavoriteState(favoriteList = it)
            }.launchIn(viewModelScope)
        }
    }

    fun deleteFavoriteRecipe(recipe: FavoriteRecipeEntity) {
        viewModelScope.launch {
            repository.delete(recipe)
        }
    }
}