package com.github.goutarouh.androidsampleapp.core.repository

import com.github.goutarouh.androidsampleapp.core.network.MyApiService

interface Data1Repository {
    val text: String
}

internal class Data1RepositoryImpl(
    myApiService: MyApiService
): Data1Repository {
    override val text: String
        get() = "Data1RepositoryImpl"
}