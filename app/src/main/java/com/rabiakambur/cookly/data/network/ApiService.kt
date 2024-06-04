package com.rabiakambur.cookly.data.network

import com.rabiakambur.cookly.model.RecipesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("recipes/complexSearch?apiKey=b58794c11d984f3796c46fe70516af79")
    suspend fun getRecipes(): RecipesResponse
}