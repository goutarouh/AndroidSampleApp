package com.github.goutarouh.simplerssreader.core.util.data

import com.github.goutarouh.simplerssreader.core.util.exception.RssException

sealed interface Result<out T> {
    data class Success<T>(val data: T): Result<T>
    data class Error(val e: RssException): Result<Nothing>
}

fun <T> Result<T>.dataOrNull(): T? {
    return if (this is Result.Success) data else null
}