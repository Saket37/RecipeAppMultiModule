package com.example.details_data.di

import com.example.details_data.network.ApiService
import com.example.details_data.repository.DetailsRepositoryImpl
import com.example.details_data.room.RecipeDetailsDAO
import com.example.details_domain.repository.DetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DetailsDataModule {
    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesDetailsRepository(apiService: ApiService,recipeDetailsDAO: RecipeDetailsDAO): DetailsRepository {
        return DetailsRepositoryImpl(apiService,recipeDetailsDAO)
    }
}