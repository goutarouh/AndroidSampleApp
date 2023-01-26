package com.github.goutarouh.simplerssreader.core.repository.di

import android.content.Context
import com.github.goutarouh.simplerssreader.core.database.TransactionProcessExecutor
import com.github.goutarouh.simplerssreader.core.database.dao.RssDao
import com.github.goutarouh.simplerssreader.core.network.service.RssService
import com.github.goutarouh.simplerssreader.core.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun rssRepository(
        @ApplicationContext context: Context,
        transactionProcessExecutor: TransactionProcessExecutor,
        RssService: RssService,
        rssDao: RssDao
    ): RssRepository {
        return RssRepositoryImpl(
            context = context,
            transactionProcessExecutor = transactionProcessExecutor,
            rssService = RssService,
            rssDao = rssDao,
        )
    }
}