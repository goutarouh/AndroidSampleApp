package com.github.goutarouh.simplerssreader.core.network.parser

import com.github.goutarouh.simplerssreader.core.util.exception.ParseException
import org.xmlpull.v1.XmlPullParser

fun XmlPullParser.readTagContent(tag: String): String {
    this.require(XmlPullParser.START_TAG, null, tag)
    val content = this.readText()
    this.require(XmlPullParser.END_TAG, null, tag)
    return content
}

fun XmlPullParser.readText(): String {
    var result = ""
    if (this.next() == XmlPullParser.TEXT) {
        result = this.text
        this.nextTag()
    }
    return result
}

fun XmlPullParser.skip() {
    if (this.eventType != XmlPullParser.START_TAG) {
        throw ParseException("")
    }
    var depth = 1
    while (depth != 0) {
        when (this.next()) {
            XmlPullParser.END_TAG -> depth--
            XmlPullParser.START_TAG -> depth++
        }
    }
}