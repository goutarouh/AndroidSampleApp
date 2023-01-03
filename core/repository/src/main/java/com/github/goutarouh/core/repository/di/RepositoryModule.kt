package com.github.goutarouh.core.repository.di

import com.github.goutarouh.core.repository.*
import com.github.goutarouh.core.repository.Data1RepositoryImpl
import com.github.goutarouh.core.repository.Data2RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun data1Repository(): Data1Repository {
        return Data1RepositoryImpl()
    }
    @Singleton
    @Provides
    fun data2Repository(): Data2Repository {
        return Data2RepositoryImpl()
    }
    @Singleton
    @Provides
    fun data3Repository(): Data3Repository {
        return Data3RepositoryImpl()
    }
}