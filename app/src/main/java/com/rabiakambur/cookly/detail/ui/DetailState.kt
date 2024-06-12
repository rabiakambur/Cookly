package com.rabiakambur.cookly.detail.ui

import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResult

data class DetailState(
    val recipesResult: RecipesResult?
) {

    fun getRecipeIngredientsAsFormatted(): String {
        return recipesResult
            ?.analyzedInstructions
            ?.first()
            ?.steps
            ?.mapNotNull { it.recipeIngredients.firstOrNull() }
            ?.joinToString("\n") { it.name }.orEmpty()
    }

    fun getRecipeStepsAsFormatted(): String {
        return recipesResult
            ?.analyzedInstructions
            ?.first()
            ?.steps
            ?.mapNotNull { it.recipeStep.firstOrNull() }
            ?.joinToString("\n").orEmpty()
    }
}