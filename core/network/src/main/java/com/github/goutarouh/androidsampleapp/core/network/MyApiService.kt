package com.github.goutarouh.androidsampleapp.core.network

import retrofit2.http.GET

interface MyApiService {

    @GET("data")
    suspend fun getData(): String

}