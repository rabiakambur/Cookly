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
            .mapNotNull { it.recipeIngredients.firstOrNull() }
            .joinToString("\n") { it.name }
    }

    fun getRecipeStepsAsFormatted(): String {
        return favoriteRecipe
            .instructions
            .first()
            .steps
            .mapNotNull { it.recipeStep.firstOrNull() }
            .joinToString("\n")
    }
}
