package com.github.goutarouh.simplerssreader.core.repository.workmanager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.github.goutarouh.simplerssreader.core.repository.RssRepository
import com.github.goutarouh.simplerssreader.core.util.data.AppConfig
import com.github.goutarouh.simplerssreader.core.util.data.Result.Error
import com.github.goutarouh.simplerssreader.core.util.data.Result.Success
import com.github.goutarouh.simplerssreader.core.util.notification.NotificationUtil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.LocalDateTime

@HiltWorker
class RssFetchWorker @AssistedInject constructor(
    @Assisted val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val rssRepository: RssRepository,
    private val appConfig: AppConfig
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val rssLink = inputData.getString(RSS_LINK) ?: return Result.failure()
        val rssTitle = inputData.getString(RSS_TITLE) ?: return Result.failure()

        val newItemCount = when (val result = rssRepository.updateRssAndCheckNewItemCount(rssLink)) {
            is Success -> {
                result.data
            }
            is Error -> {
                return createWorkFailure(rssLink, result.e)
            }
        }

        try {
            rssRepository.setUnReadItemCount(rssLink, newItemCount)
        } catch (e: Exception) {
            return createWorkFailure(rssLink, e)
        }

        if (NotificationUtil.areNotificationEnabled(appContext) && newItemCount > 0) {
            appConfig.postNotification(rssLink, rssTitle, newItemCount)
        }

        val successData = workDataOf(
            RSS_LINK to rssLink,
            RSS_FETCHED_AT to LocalDateTime.now().toString(),
            RSS_NEW_ITEM_COUNT to newItemCount.toString()
        )

        return Result.success(successData)
    }

    private fun createWorkFailure(
        rssLink: String,
        e: Exception
    ): Result {
        return Result.failure(
            workDataOf(
                RSS_LINK to rssLink,
                RSS_FETCHED_AT to LocalDateTime.now().toString(),
                RSS_ERROR_MESSAGE to (e.message ?: "unknown")
            )
        )
    }

    companion object {
        const val RSS_LINK = "RSS_LINK"
        const val RSS_TITLE = "RSS_TITLE"

        const val RSS_FETCHED_AT = "RSS_FETCHED_AT"
        const val RSS_NEW_ITEM_COUNT = "NEW_ITEM_COUNT"
        const val RSS_ERROR_MESSAGE = "RSS_ERROR_MESSAGE"
    }
}