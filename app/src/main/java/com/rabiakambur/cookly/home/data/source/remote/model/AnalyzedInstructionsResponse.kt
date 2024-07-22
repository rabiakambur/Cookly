package com.rabiakambur.cookly.home.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class AnalyzedInstructionsResponse(
    @SerializedName("steps")
    val steps: List<StepResponse?>?
)
