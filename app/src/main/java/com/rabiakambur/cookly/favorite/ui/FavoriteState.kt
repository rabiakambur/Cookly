package com.rabiakambur.cookly.favorite.ui

import com.rabiakambur.cookly.favorite.data.source.local.RecipeEntity

data class FavoriteState (
    val favoriteList: List<RecipeEntity> = emptyList()
)