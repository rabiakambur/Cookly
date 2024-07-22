package com.rabiakambur.cookly.home.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("results")
    val results: List<RecipesResult?>?
)