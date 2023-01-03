package com.github.goutarouh.core.repository

import com.github.goutarouh.core.network.MyApiService

interface Data3Repository {
    val text: String
}

internal class Data3RepositoryImpl(
    myApiService: MyApiService
): Data3Repository {
    override val text: String
        get() = "Data3RepositoryImpl"
}