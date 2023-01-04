package com.github.goutarouh.androidsampleapp.core.json

import org.junit.Test
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert

class SimpleDataClassTest {
    @Test
    fun encode_isCorrect() {
        val actual = Json.encodeToString(decodedData)
        Assert.assertEquals(encodedData, actual)
    }

    @Test
    fun decode_isCorrect() {
        val actual = Json.decodeFromString<SDCProject>(encodedData)
        Assert.assertEquals(decodedData, actual)
    }
}

@Serializable
private data class SDCProject(val name: String, val language: String)
private val decodedData = SDCProject("kotlinx.serialization", "Kotlin")
private const val encodedData = """{"name":"kotlinx.serialization","language":"Kotlin"}"""