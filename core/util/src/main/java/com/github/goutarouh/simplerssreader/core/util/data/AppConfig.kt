package com.github.goutarouh.simplerssreader.core.util.data

data class AppConfig(
    val isDebug: Boolean,
    val postNotification: (String, String, Int) -> Unit
)
