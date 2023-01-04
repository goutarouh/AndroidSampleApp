package com.github.goutarouh.androidsampleapp.di

import com.github.goutarouh.androidsampleapp.BuildConfig
import com.github.goutarouh.androidsampleapp.core.util.data.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun appConfig(): AppConfig {
        return AppConfig(
            isDebug = BuildConfig.DEBUG
        )
    }

}