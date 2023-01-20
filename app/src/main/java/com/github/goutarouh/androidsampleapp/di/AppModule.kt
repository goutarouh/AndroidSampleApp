package com.github.goutarouh.androidsampleapp.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.github.goutarouh.androidsampleapp.BuildConfig
import com.github.goutarouh.androidsampleapp.MainActivity
import com.github.goutarouh.androidsampleapp.R
import com.github.goutarouh.androidsampleapp.core.util.data.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun appConfig(
        @ApplicationContext context: Context
    ): AppConfig {
        return AppConfig(
            isDebug = BuildConfig.DEBUG,
            postNotification = { link, title ->
                postNotification(context, link, title)
            }
        )
    }

    private fun postNotification(
        context: Context,
        rssLink: String,
        rssTitle: String
    ) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val channelId = context.getString(R.string.rss_update_channel_id)
        val title = context.getString(R.string.rss_update_notification_title)
        val text = context.getString(R.string.rss_update_notification_text, rssTitle)

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.rss_feed)
            .setContentTitle(title)
            .setContentText(text)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(rssLink.hashCode(), builder.build())
        }
    }

}