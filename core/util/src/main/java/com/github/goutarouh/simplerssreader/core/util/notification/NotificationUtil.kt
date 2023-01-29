package com.github.goutarouh.simplerssreader.core.util.notification

import android.app.NotificationManager
import android.content.Context

object NotificationUtil {

    fun areNotificationEnabled(context: Context): Boolean {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager ?: return false
        return nm.areNotificationsEnabled()
    }

}