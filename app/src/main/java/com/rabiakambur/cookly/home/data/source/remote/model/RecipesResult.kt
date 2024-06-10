package com.rabiakambur.cookly.home.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class RecipesResult(
    @SerializedName("id")
    val recipeId: String,

    @SerializedName("title")
    val recipeTitle: String,

    @SerializedName("image")
    val recipeImage: String,

    @SerializedName("readyInMinutes")
    val recipeReadyInMinutes: Int,

    @SerializedName("servings")
    val recipeServings: Int,

    @SerializedName("dishTypes")
    val dishTypes: List<String>,

    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstructionsResponse>
)