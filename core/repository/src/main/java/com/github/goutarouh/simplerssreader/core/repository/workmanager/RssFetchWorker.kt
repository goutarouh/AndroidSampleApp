package com.github.goutarouh.simplerssreader.core.repository.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.github.goutarouh.simplerssreader.core.repository.RssRepository
import com.github.goutarouh.simplerssreader.core.util.data.AppConfig
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class RssFetchWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val rssRepository: RssRepository,
    private val appConfig: AppConfig
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val rssLink = inputData.getString(RSS_LINK) ?: return Result.failure()
        val rssTitle = inputData.getString(RSS_TITLE) ?: return Result.failure()

        val newItemCount = rssRepository.updateRssAndCheckNewItemCount(rssLink)
        try {
            rssRepository.setUnReadItemCount(rssLink, newItemCount)
        } catch (e: Exception) {
            return Result.retry()
        }

        appConfig.postNotification(rssLink, rssTitle)

        return Result.success()
    }

    companion object {
        const val RSS_LINK = "RSS_LINK"
        const val RSS_TITLE = "RSS_TITLE"
    }
}