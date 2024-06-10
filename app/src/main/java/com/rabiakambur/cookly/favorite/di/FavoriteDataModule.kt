package com.rabiakambur.cookly.favorite.di

import android.app.Application
import androidx.room.Room
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteDao
import com.rabiakambur.cookly.favorite.data.source.local.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDataModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): FavoriteDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteDatabase::class.java,
            "recipe_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(db: FavoriteDatabase): FavoriteDao {
        return db.favoriteDao()
    }
}