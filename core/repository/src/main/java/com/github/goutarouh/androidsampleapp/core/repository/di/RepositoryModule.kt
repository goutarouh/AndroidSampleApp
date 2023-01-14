package com.github.goutarouh.androidsampleapp.core.repository.di

import com.github.goutarouh.androidsampleapp.core.database.TransactionProcessExecutor
import com.github.goutarouh.androidsampleapp.core.database.dao.RssDao
import com.github.goutarouh.androidsampleapp.core.network.MyApiService
import com.github.goutarouh.androidsampleapp.core.network.service.ZennRssService
import com.github.goutarouh.androidsampleapp.core.repository.*
import com.github.goutarouh.androidsampleapp.core.repository.Data2RepositoryImpl
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
    fun rssRepository(
        transactionProcessExecutor: TransactionProcessExecutor,
        zennRssService: ZennRssService,
        rssDao: RssDao
    ): RssRepository {
        return RssRepositoryImpl(
            transactionProcessExecutor = transactionProcessExecutor,
            zennRssService = zennRssService,
            rssDao = rssDao,
        )
    }
    @Singleton
    @Provides
    fun data2Repository(
        myApiService: MyApiService
    ): Data2Repository {
        return Data2RepositoryImpl(
            myApiService = myApiService
        )
    }
    @Singleton
    @Provides
    fun data3Repository(
        myApiService: MyApiService
    ): Data3Repository {
        return Data3RepositoryImpl(
            myApiService = myApiService
        )
    }
}