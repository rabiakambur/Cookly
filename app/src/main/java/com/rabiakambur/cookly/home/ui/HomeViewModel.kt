package com.rabiakambur.cookly.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabiakambur.cookly.home.data.source.remote.HomeClient
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResultResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _recipesListFlow: MutableStateFlow<List<RecipesResultResponse>> = MutableStateFlow(
        listOf()
    )

    val recipesListFlow: StateFlow<List<RecipesResultResponse>> = _recipesListFlow

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            val recipes = HomeClient.retrofit.getRecipes()
            _recipesListFlow.value = recipes.results
        }
    }
}