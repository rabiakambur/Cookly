package com.rabiakambur.cookly.home.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class IngredientResponse(
    @SerializedName("name")
    val name: String
)
