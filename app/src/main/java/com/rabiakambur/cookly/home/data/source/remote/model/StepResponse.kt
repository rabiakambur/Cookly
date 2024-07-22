package com.rabiakambur.cookly.home.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class StepResponse(
    @SerializedName("step")
    val recipeStep: String?,

    @SerializedName("ingredients")
    val recipeIngredients: List<IngredientResponse?>?
)
