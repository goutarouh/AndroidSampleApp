package com.github.goutarouh.simplerssreader.core.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class SerialFieldNamesTest {

    @Test
    fun encode_isCorrect() {
        val actual = Json.encodeToString(decodeData)
        Assert.assertEquals(encodedData, actual)
    }

    @Test
    fun decode_isCorrect() {
        val actual = Json.decodeFromString<SFNProject>(encodedData)
        Assert.assertEquals(decodeData, actual)
    }


}

@Serializable
private data class SFNProject(
    @SerialName("is_going")
    val isGoing: Boolean,
    @SerialName("lang")
    val language: String
)
private val decodeData = SFNProject(true, "jn")
private const val encodedData = """{"is_going":true,"lang":"jn"}"""
