package com.rabiakambur.cookly.home.ui

import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResult

data class HomeState(
    val recipesList: List<RecipesResult?>? = listOf(),
    val filteredRecipesList: List<RecipesResult?>? = listOf(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)