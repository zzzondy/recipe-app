package com.recipeapp.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.recipeapp.network.BuildConfig
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideDebugOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(client)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
}