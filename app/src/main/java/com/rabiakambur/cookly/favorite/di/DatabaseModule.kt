package com.rabiakambur.cookly.favorite.di

import android.app.Application
import androidx.room.Room
import com.rabiakambur.cookly.favorite.data.source.local.RecipeDao
import com.rabiakambur.cookly.favorite.data.source.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RecipeDatabase {
        return Room.databaseBuilder(app, RecipeDatabase::class.java, "recipe_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(db: RecipeDatabase): RecipeDao {
        return db.recipeDao()
    }
}