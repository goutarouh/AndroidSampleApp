package com.github.goutarouh.simplerssreader.core.network

import com.github.goutarouh.simplerssreader.core.util.data.Result
import com.github.goutarouh.simplerssreader.core.util.exception.ApiException
import com.github.goutarouh.simplerssreader.core.util.exception.PageNotFoundException
import com.github.goutarouh.simplerssreader.core.util.exception.ParseException
import org.xmlpull.v1.XmlPullParserException
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> rssSafeCall(rssLink: String, apiCall: suspend () -> T): Result<T> {
    return try {
        val data = apiCall()
        Result.Success(data)
    } catch (e: HttpException) {
        val rssError = when (e.code()) {
            404 -> PageNotFoundException(rssLink)
            else -> ApiException(rssLink)
        }
        Result.Error(rssError)
    } catch (e: UnknownHostException) {
        Result.Error(PageNotFoundException(rssLink))
    } catch (e: XmlPullParserException) {
        Result.Error(ParseException(rssLink))
    } catch (e: ParseException) {
        Result.Error(ParseException(rssLink))
    } catch (e: Exception) {
        Result.Error(ApiException(rssLink))
    }
}