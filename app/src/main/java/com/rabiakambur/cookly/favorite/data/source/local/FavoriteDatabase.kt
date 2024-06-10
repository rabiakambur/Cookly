package com.rabiakambur.cookly.favorite.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rabiakambur.cookly.favorite.data.source.local.converter.FavoriteDishTypeConverter
import com.rabiakambur.cookly.favorite.data.source.local.converter.FavoriteIngredientTypeConverter

@Database(
    entities = [FavoriteRecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(FavoriteIngredientTypeConverter::class, FavoriteDishTypeConverter::class)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}