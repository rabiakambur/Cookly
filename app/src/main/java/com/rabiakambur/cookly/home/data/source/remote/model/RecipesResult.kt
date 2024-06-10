package com.rabiakambur.cookly.home.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class RecipesResult(
    @SerializedName("id")
    val recipeId: String,

    @SerializedName("title")
    val recipeTitle: String,

    @SerializedName("image")
    val recipeImage: String
)