package com.rabiakambur.cookly.home.data.source.remote.repository

import com.rabiakambur.cookly.home.data.source.remote.HomeService
import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeService
) {

    suspend fun getAllRecipe(): Flow<RecipesResponse>{
        return flow {
            emit(homeApi.getRecipes())
        }
    }
}