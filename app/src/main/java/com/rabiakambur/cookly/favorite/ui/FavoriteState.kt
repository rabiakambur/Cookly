package com.rabiakambur.cookly.favorite.ui

import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity

data class FavoriteState (
    val favoriteList: List<FavoriteRecipeEntity> = emptyList()
)