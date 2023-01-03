package com.github.goutarouh.core.repository

import com.github.goutarouh.core.network.MyApiService

interface Data2Repository {
    val text: String
}

internal class Data2RepositoryImpl(
    myApiService: MyApiService
): Data2Repository {
    override val text: String
        get() = "Data2RepositoryImpl"
}