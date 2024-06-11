package com.rabiakambur.cookly.home.data.source.remote

import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResponse
import retrofit2.http.GET

interface HomeService {
    @GET("recipes/complexSearch?instructionsRequired=true&fillIngredients=true&addRecipeInformation=true")
    suspend fun getRecipes(): RecipesResponse
}