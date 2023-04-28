package com.example.commons.network

import com.example.commons.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.TASTY_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(client).build()
    }

    @Provides
    fun provideClientBuilder(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
            //if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        }
        val headerAPIInterceptor = Interceptor {
            val request = it.request().newBuilder().addHeader(
                Constants.RAPID_API_KEY_HEADER, Constants.RAPID_API_KEY
            ).build()
            it.proceed(request)
        }
        val headerHostInterceptor = Interceptor {
            val request =
                it.request().newBuilder()
                    .addHeader(Constants.RAPID_HOST_KEY_HEADER, Constants.RAPID_HOST_KEY)
                    .build()
            it.proceed(request)
        }
        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(logInterceptor)
            addInterceptor(headerAPIInterceptor)
            addInterceptor(headerHostInterceptor)
            connectTimeout(2, TimeUnit.MINUTES)
            readTimeout(2, TimeUnit.MINUTES)
            writeTimeout(4, TimeUnit.MINUTES)
        }
        return httpClient.build()
    }
}