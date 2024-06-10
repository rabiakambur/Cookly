package com.rabiakambur.cookly.favorite.data.repository

import com.rabiakambur.cookly.favorite.data.source.local.FavoriteDao
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val recipeDao: FavoriteDao
) {

    fun getFavoriteRecipes(): Flow<List<FavoriteRecipeEntity>> {
        return recipeDao.getFavoriteRecipes()
    }

    suspend fun delete(favoriteRecipeEntity: FavoriteRecipeEntity) {
        recipeDao.deleteRecipe(favoriteRecipeEntity)
    }
}