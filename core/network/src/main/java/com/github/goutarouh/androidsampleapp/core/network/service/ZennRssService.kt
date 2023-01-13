package com.github.goutarouh.androidsampleapp.core.network.service

import com.github.goutarouh.androidsampleapp.core.network.data.rss.RssApiModel
import retrofit2.http.GET

interface ZennRssService {

    @GET("/topics/android/feed")
    suspend fun getRss(): RssApiModel

}