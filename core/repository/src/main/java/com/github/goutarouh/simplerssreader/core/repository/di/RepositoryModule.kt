package com.github.goutarouh.simplerssreader.core.repository.di

import android.content.Context
import com.github.goutarouh.simplerssreader.core.database.TransactionProcessExecutor
import com.github.goutarouh.simplerssreader.core.database.dao.RssDao
import com.github.goutarouh.simplerssreader.core.network.service.ZennRssService
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
        zennRssService: ZennRssService,
        rssDao: RssDao
    ): RssRepository {
        return RssRepositoryImpl(
            context = context,
            transactionProcessExecutor = transactionProcessExecutor,
            zennRssService = zennRssService,
            rssDao = rssDao,
        )
    }
}