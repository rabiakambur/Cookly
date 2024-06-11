package com.rabiakambur.cookly.home.data.repository

import com.rabiakambur.cookly.favorite.data.source.local.FavoriteDao
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.home.data.source.remote.HomeService
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeService,
    private val favoriteDao: FavoriteDao
) {

    suspend fun getAllRecipe(): Flow<RecipesResponse> {
        return flow {
            val response = homeApi.getRecipes()
            val updatedResults = response.results.map { recipe ->
                val isFavorite = favoriteDao.getFavoriteRecipeByTitle(title = recipe.recipeTitle) != null
                recipe.copy(isFavorite = isFavorite)
            }
            emit(response.copy(results = updatedResults))
        }
    }

    fun getFavoriteRecipes(): Flow<List<FavoriteRecipeEntity>> {
        return favoriteDao.getFavoriteRecipes()
    }

    suspend fun deleteRecipeByTitle(title: String) {
        favoriteDao.deleteRecipeByTitle(title)
    }

    suspend fun addRecipeToFavorite(favoriteRecipeEntity: FavoriteRecipeEntity) {
        favoriteDao.insertRecipe(favoriteRecipeEntity)
    }
}