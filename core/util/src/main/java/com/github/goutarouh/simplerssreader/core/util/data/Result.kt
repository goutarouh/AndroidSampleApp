package com.github.goutarouh.simplerssreader.core.util.data

sealed interface Result<out T> {
    data class Success<T>(val data: T): Result<T>
    data class Error(val e: Exception): Result<Nothing>
}

fun <T> Result<T>.dataOrNull(): T? {
    return if (this is Result.Success) data else null
}