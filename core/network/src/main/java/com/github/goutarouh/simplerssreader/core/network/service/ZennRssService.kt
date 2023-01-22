package com.github.goutarouh.simplerssreader.core.network.service

import com.github.goutarouh.simplerssreader.core.network.data.rss.RssApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ZennRssService {

    @GET
    suspend fun getRss(
        @Url resString: String
    ): RssApiModel

}