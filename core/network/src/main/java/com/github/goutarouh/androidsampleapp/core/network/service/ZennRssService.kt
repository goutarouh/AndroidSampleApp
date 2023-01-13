package com.github.goutarouh.androidsampleapp.core.network.service

import com.github.goutarouh.androidsampleapp.core.network.data.rss.RssApiModel
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