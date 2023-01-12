package com.github.goutarouh.androidsampleapp.core.database.di

import android.content.Context
import androidx.room.Room
import com.github.goutarouh.androidsampleapp.core.database.AppDatabase
import com.github.goutarouh.androidsampleapp.core.database.dao.RssDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "android-sample-app-db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRssDao(
        db: AppDatabase
    ): RssDao {
        return db.rssDao()
    }

}