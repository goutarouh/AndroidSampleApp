package com.github.goutarouh.androidsampleapp.core.repository

import com.github.goutarouh.androidsampleapp.core.network.MyApiService

interface Data3Repository {
    val text: String
}

internal class Data3RepositoryImpl(
    myApiService: MyApiService
): Data3Repository {
    override val text: String
        get() = "Data3RepositoryImpl"
}