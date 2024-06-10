package com.rabiakambur.cookly.favorite.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rabiakambur.cookly.home.data.source.remote.model.AnalyzedInstructionsResponse

@Entity(tableName = "recipes")
data class FavoriteRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val recipeImage: String,
    val recipeTitle: String,
    val readyInMinutes: Int,
    val recipeServings: Int,
    val dishTypes: List<String>,
    val instructions: List<AnalyzedInstructionsResponse>
)