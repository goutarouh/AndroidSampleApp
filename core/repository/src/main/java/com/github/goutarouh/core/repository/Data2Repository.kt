package com.github.goutarouh.core.repository

interface Data2Repository {
    val text: String
}

internal class Data2RepositoryImpl: Data2Repository {
    override val text: String
        get() = "Data2RepositoryImpl"
}