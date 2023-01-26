package com.github.goutarouh.simplerssreader.core.network.service

import com.github.goutarouh.simplerssreader.core.network.data.rss.RssApiModel
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {

    @GET
    suspend fun getRss(
        @Url resString: String
    ): RssApiModel

}