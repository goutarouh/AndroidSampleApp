package com.github.goutarouh.simplerssreader.core.json

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class IgnoreTest {
    @Test
    fun encode_isCorrect() {
        val actual = Json.encodeToString(decodedData)
        Assert.assertEquals(encodedData, actual)
    }

    @Test
    fun decode_isCorrect() {
        val actual = Json {
            ignoreUnknownKeys = true
        }.decodeFromString<IProject>(encodedDataIncludeUnknown)
        Assert.assertEquals(decodedData, actual)
    }

    // TODO JsonDecodingExceptionでキャッチしたい
    @Test(expected = Exception::class)
    fun decode_throwError() {
        val actual = Json.decodeFromString<IProject>(encodedDataIncludeUnknown)
    }
}

@Serializable
private data class IProject(val name: String)
private val decodedData = IProject("kotlin")
private const val encodedDataIncludeUnknown = """{"name":"kotlin","age":23}"""
private const val encodedData = """{"name":"kotlin"}"""