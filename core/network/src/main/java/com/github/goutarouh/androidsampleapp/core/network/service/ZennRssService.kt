package com.github.goutarouh.androidsampleapp.core.network.service

import com.github.goutarouh.androidsampleapp.core.network.data.rss.Rss
import retrofit2.http.GET

interface ZennRssService {

    @GET("/topics/android/feed")
    suspend fun getData(): Rss

}