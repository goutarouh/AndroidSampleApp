package com.github.goutarouh.androidsampleapp.core.network.di

import com.github.goutarouh.androidsampleapp.core.network.MyApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.github.goutarouh.androidsampleapp.core.util.data.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        json: Json,
        appConfig: AppConfig
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun provideMyApiService(
        retrofit: Retrofit
    ): MyApiService {
        return retrofit.create(MyApiService::class.java)
    }
}
