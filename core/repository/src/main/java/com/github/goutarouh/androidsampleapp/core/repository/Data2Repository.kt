package com.github.goutarouh.androidsampleapp.core.repository

import com.github.goutarouh.androidsampleapp.core.network.MyApiService

interface Data2Repository {
    val text: String
}

internal class Data2RepositoryImpl(
    myApiService: MyApiService
): Data2Repository {
    override val text: String
        get() = "Data2RepositoryImpl"
}