package com.github.goutarouh.androidsampleapp.core.network.di

import com.github.goutarouh.androidsampleapp.core.network.MyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
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
