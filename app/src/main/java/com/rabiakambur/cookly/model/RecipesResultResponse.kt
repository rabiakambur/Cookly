package com.rabiakambur.cookly.model

import com.google.gson.annotations.SerializedName

data class RecipesResultResponse(
    @SerializedName("id")
    val recipeId: String,

    @SerializedName("title")
    val recipeTitle: String,

    @SerializedName("image")
    val recipeImage: String
)