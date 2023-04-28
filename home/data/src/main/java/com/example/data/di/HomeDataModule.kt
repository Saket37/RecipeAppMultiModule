package com.example.data.di

import com.example.data.network.ApiService
import com.example.data.repository.HomeRepositoryImpl
import com.example.data.room.RecipeDAO
import com.example.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeDataModule {
    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun providesHomeRepository(apiService: ApiService,recipeDAO: RecipeDAO): HomeRepository {
        return HomeRepositoryImpl(apiService,recipeDAO)
    }
}