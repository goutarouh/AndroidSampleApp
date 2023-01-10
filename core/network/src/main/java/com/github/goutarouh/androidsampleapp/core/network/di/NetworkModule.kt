package com.github.goutarouh.androidsampleapp.core.network.di

import com.github.goutarouh.androidsampleapp.core.network.MyApiService
import com.github.goutarouh.androidsampleapp.core.network.factory.RssConverterFactory
import com.github.goutarouh.androidsampleapp.core.network.service.ZennRssService
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

//    @Singleton
//    @Provides
//    fun provideJsonRetrofit(
//        json: Json,
//        appConfig: AppConfig
//    ): Retrofit {
//        val contentType = "application/json".toMediaType()
//        return Retrofit.Builder()
//            .baseUrl("https://api.github.com/")
//            .addConverterFactory(json.asConverterFactory(contentType))
//            .build()
//    }

    @Singleton
    @Provides
    fun provideXmlRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://zenn.dev")
            .addConverterFactory(RssConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideZennRssService(
        retrofit: Retrofit
    ): ZennRssService {
        return retrofit.create(ZennRssService::class.java)
    }

    @Singleton
    @Provides
    fun provideMyApiService(
        retrofit: Retrofit
    ): MyApiService {
        return retrofit.create(MyApiService::class.java)
    }
}
