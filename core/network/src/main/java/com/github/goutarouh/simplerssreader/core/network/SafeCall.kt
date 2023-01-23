package com.github.goutarouh.simplerssreader.core.network

import com.github.goutarouh.simplerssreader.core.util.data.Result
import com.github.goutarouh.simplerssreader.core.util.exception.PageNotFoundException
import retrofit2.HttpException

suspend fun <T> safeCall(apiCall: suspend () -> T): Result<T> {
    return try {
        val data = apiCall()
        Result.Success(data)
    } catch (e: HttpException) {
        val rssError = when (e.code()) {
            404 -> PageNotFoundException(e.message())
            else -> e
        }
        Result.Error(rssError)
    }
}