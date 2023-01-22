package com.github.goutarouh.simplerssreader.core.network

import retrofit2.http.GET

interface MyApiService {

    @GET("data")
    suspend fun getData(): String

}