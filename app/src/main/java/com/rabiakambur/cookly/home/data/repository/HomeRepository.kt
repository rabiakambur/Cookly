package com.rabiakambur.cookly.home.data.repository

import com.rabiakambur.cookly.favorite.data.source.local.FavoriteDao
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.home.data.source.remote.HomeService
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResponse
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResult
import com.rabiakambur.cookly.main.util.Async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeService,
    private val favoriteDao: FavoriteDao
) {

    suspend fun getAllRecipe(): Flow<Async<RecipesResponse>> {
        return flow<Async<RecipesResponse>> {
            val response = homeApi.getRecipes()
            val updatedResults = updateRecipeFavorites(response.results)
            emit(Async.Success(response.copy(results = updatedResults)))
        }.onStart {
            emit(Async.Loading())
        }.catch { cause ->
            emit(Async.Error(cause))
        }
    }

    private suspend fun updateRecipeFavorites(recipes: List<RecipesResult>): List<RecipesResult> {
        return recipes.map { recipe ->
            val isFavorite = isRecipeFavorite(recipe.recipeTitle)
            recipe.copy(isFavorite = isFavorite)
        }
    }

    private suspend fun isRecipeFavorite(title: String): Boolean {
        return favoriteDao.getFavoriteRecipeByTitle(title) != null
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