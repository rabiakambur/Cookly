package com.rabiakambur.cookly.favorite.data.source.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rabiakambur.cookly.home.data.source.remote.model.AnalyzedInstructionsResponse

class FavoriteIngredientTypeConverter {

    @TypeConverter
    fun fromAnalyzedInstructionsResponseList(value: List<AnalyzedInstructionsResponse>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<AnalyzedInstructionsResponse>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toAnalyzedInstructionsResponseList(value: String?): List<AnalyzedInstructionsResponse>? {
        val gson = Gson()
        val type = object : TypeToken<List<AnalyzedInstructionsResponse>>() {}.type
        return gson.fromJson(value, type)
    }
}