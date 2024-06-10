package com.rabiakambur.cookly.favorite.data.source.local.repository

import com.rabiakambur.cookly.favorite.data.source.local.RecipeDao
import com.rabiakambur.cookly.favorite.data.source.local.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRecipeRepository @Inject constructor(
    private val recipeDao: RecipeDao
) {

    suspend fun getFavoriteRecipes(): Flow<List<RecipeEntity>> {
        return recipeDao.getFavoriteRecipes()
    }

    suspend fun delete(recipeEntity: RecipeEntity) {
        recipeDao.deleteRecipe(recipeEntity)
    }
}