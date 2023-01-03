package com.github.goutarouh.core.repository

interface Data3Repository {
    val text: String
}

internal class Data3RepositoryImpl: Data3Repository {
    override val text: String
        get() = "Data3RepositoryImpl"
}