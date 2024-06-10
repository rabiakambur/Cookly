package com.rabiakambur.cookly.home.data.repository

import com.rabiakambur.cookly.favorite.data.source.local.FavoriteDao
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteRecipeEntity
import com.rabiakambur.cookly.home.data.source.remote.HomeService
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeService,
    private val favoriteDao: FavoriteDao
) {

    suspend fun getAllRecipe(): Flow<RecipesResponse>{
        return flow {
            emit(homeApi.getRecipes())
        }
    }

    suspend fun addRecipeToFavorite(favoriteRecipeEntity: FavoriteRecipeEntity) {
        favoriteDao.insertRecipe(favoriteRecipeEntity)
    }
}