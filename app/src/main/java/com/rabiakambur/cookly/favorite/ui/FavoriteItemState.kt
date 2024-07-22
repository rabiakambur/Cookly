package com.rabiakambur.cookly.favorite.ui

import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity

data class FavoriteItemState(
    val favoriteRecipe: FavoriteRecipeEntity,
) {

    fun getRecipeImage(): String {
        return favoriteRecipe.recipeImage
    }

    fun getRecipeIngredientsAsFormatted(): String {
        return favoriteRecipe
            .instructions
            .first()
            .steps
            ?.mapNotNull { it?.recipeIngredients?.firstOrNull() }
            ?.joinToString("\n") { it.name.toString() }.orEmpty()
    }

    fun getRecipeStepsAsFormatted(): String {
        return favoriteRecipe
            .instructions
            .first()
            .steps
            ?.mapIndexed { index, stepResponse -> "${index + 1}. ${stepResponse?.recipeStep}" }
            ?.joinToString("\n").orEmpty()
    }
}
