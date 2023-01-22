package com.github.goutarouh.simplerssreader.core.util.string

import java.util.Base64

fun String.encode64(): String {
    return Base64.getEncoder().encodeToString(this.toByteArray())
}

fun String.decode64(): String {
    return String(Base64.getDecoder().decode(this))
}