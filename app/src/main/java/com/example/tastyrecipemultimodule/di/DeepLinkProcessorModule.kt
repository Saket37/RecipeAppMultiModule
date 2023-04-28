package com.example.tastyrecipemultimodule.di

import com.example.commons.utils.DeeplinkProcessor
import com.example.presentation.deeplink.HomeDeeplinkProcessor
import com.example.details_presentation.deeplink.RecipeDetailsDeeplinkProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface DeepLinkProcessorModule {
    @Binds
    @IntoSet
    fun bindHomeProcessor(homeDeeplinkProcessor: HomeDeeplinkProcessor): DeeplinkProcessor

    @Binds
    @IntoSet
    fun bindRecipeDetailsProcessor(recipeDetailsDeeplinkProcessor: RecipeDetailsDeeplinkProcessor): DeeplinkProcessor
}