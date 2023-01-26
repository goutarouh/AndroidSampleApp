package com.github.goutarouh.simplerssreader.core.network.di

import com.github.goutarouh.simplerssreader.core.network.MyApiService
import com.github.goutarouh.simplerssreader.core.network.factory.RssConverterFactory
import com.github.goutarouh.simplerssreader.core.network.service.RssService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideXmlRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://anything-for-rss") // Add this line not to crash
            .addConverterFactory(RssConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideZennRssService(
        retrofit: Retrofit
    ): RssService {
        return retrofit.create(RssService::class.java)
    }

    @Singleton
    @Provides
    fun provideMyApiService(
        retrofit: Retrofit
    ): MyApiService {
        return retrofit.create(MyApiService::class.java)
    }
}
