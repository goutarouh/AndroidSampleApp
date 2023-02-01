package com.github.goutarouh.simplerssreader.core.util.navigation

import com.github.goutarouh.simplerssreader.core.util.string.decode64
import com.github.goutarouh.simplerssreader.core.util.string.encode64

object UrlNavArg {
    fun String.navArgEncode(): String {
        return this.encode64().replace("/", "_")
    }

    fun String.navArgDecode(): String {
        return this.replace("_", "/").decode64()
    }
}