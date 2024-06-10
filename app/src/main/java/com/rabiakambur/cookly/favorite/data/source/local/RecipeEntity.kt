package com.rabiakambur.cookly.favorite.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val name: String,
    val image: String,
    val isFavorite: Boolean,

)