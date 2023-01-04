package com.github.goutarouh.androidsampleapp.core.json

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class ListTest {
    @Test
    fun encode_isCorrect() {
        val actual = Json.encodeToString(decodedData)
        Assert.assertEquals(encodedData, actual)
    }

    @Test
    fun decode_isCorrect() {
        val actual = Json.decodeFromString<List<LProject>>(encodedData)
        Assert.assertEquals(decodedData, actual)
    }
}

@Serializable
private data class LProject(val name: String)
private val decodedData = listOf(
    LProject("a"), LProject("b")
)
private val encodedData = """
    [{"name":"a"},{"name":"b"}]
""".trimIndent()