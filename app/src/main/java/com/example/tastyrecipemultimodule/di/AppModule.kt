package com.example.tastyrecipemultimodule.di

import android.content.Context
import com.example.commons.deeplink.DefaultDeeplinkHandler
import com.example.commons.utils.DeeplinkHandler
import com.example.commons.utils.DeeplinkProcessor
import com.example.data.room.RecipeDAO
import com.example.details_data.room.RecipeDetailsDAO
import com.example.tastyrecipemultimodule.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun providesDefaultDeeplinkHandler(
        processors: Set<@JvmSuppressWildcards DeeplinkProcessor>
    ): DeeplinkHandler = DefaultDeeplinkHandler(processors)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providesRecipeDao(appDatabase: AppDatabase): RecipeDAO {
        return appDatabase.getRecipeDao()
    }

    @Provides
    fun provideRecipeDetailsDao(appDatabase: AppDatabase): RecipeDetailsDAO {
        return appDatabase.getRecipeDetailDao()
    }
}