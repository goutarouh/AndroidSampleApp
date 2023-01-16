package com.github.goutarouh.androidsampleapp.core.util.localdate

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formatForUi(): String {
    return this.format(dateTimeFormatter)
}

val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd HH:mm")