package com.github.goutarouh.androidsampleapp.core.util.data

data class AppConfig(
    val isDebug: Boolean,
    val postNotification: (String, String) -> Unit
)
