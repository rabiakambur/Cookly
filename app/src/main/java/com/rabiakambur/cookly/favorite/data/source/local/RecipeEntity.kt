package com.rabiakambur.cookly.favorite.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val recipeImage: String,
    val recipeTitle: String,
    val readyInMinutes: Int,
    val recipeServings: Int,
    val dishTypes: String,
    val recipeStep: String,
    val recipeIngredients: String,
    val isFavorite: Boolean,

)