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
            postNotification = {
                postNotification(context, "Rss")
            }
        )
    }

    private fun postNotification(
        context: Context,
        title: String
    ) {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(context, "rss update")
            .setSmallIcon(R.drawable.rss_feed)
            .setContentTitle(title)
            .setContentText(context.getString(R.string.rss_update_notification_text))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(context)) {
            notify(12345, builder.build())
        }
    }

}