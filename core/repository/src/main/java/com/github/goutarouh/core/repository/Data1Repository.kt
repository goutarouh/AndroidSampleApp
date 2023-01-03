package com.github.goutarouh.core.repository

interface Data1Repository {
    val text: String
}

internal class Data1RepositoryImpl: Data1Repository {
    override val text: String
        get() = "Data1RepositoryImpl"
}