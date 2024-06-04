package com.rabiakambur.cookly.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabiakambur.cookly.data.network.ApiClient
import com.rabiakambur.cookly.model.RecipesResultResponse
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
            val recipes = ApiClient.retrofit.getRecipes()
            _recipesListFlow.value = recipes.results
        }
    }
}