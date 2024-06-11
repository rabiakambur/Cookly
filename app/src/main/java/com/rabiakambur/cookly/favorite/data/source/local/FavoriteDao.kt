package com.rabiakambur.cookly.favorite.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM recipes")
    fun getFavoriteRecipes(): Flow<List<FavoriteRecipeEntity>>

    @Query("SELECT * FROM recipes WHERE :title = recipeTitle")
    fun getFavoriteRecipeByTitle(title: String): FavoriteRecipeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(favoriteRecipeEntity: FavoriteRecipeEntity)

    @Delete
    suspend fun deleteRecipe(favoriteRecipeEntity: FavoriteRecipeEntity)

    @Query("DELETE FROM recipes WHERE recipeTitle = :title")
    suspend fun deleteRecipeByTitle(title: String)
}