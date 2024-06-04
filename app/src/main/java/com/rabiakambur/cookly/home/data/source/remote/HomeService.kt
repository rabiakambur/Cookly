package com.rabiakambur.cookly.home.data.source.remote

import com.rabiakambur.cookly.home.data.source.remote.model.RecipesResponse
import retrofit2.http.GET

interface HomeService {
    @GET("recipes/complexSearch?apiKey=b58794c11d984f3796c46fe70516af79")
    suspend fun getRecipes(): RecipesResponse
}