package com.github.goutarouh.androidsampleapp.core.network.parser

import android.util.Xml
import com.github.goutarouh.androidsampleapp.core.network.data.rss.Rss
import com.github.goutarouh.androidsampleapp.core.network.data.rss.RssItem
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

/**
 * 参考：https://developer.android.com/training/basics/network-ops/xml?hl=ja#consume
 */
class RssParser {
    fun parse(input: InputStream): Rss {
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(input, null)
        parser.nextTag()
        return readRss(parser)
    }

    internal fun readRss(parser: XmlPullParser): Rss {
        parser.require(XmlPullParser.START_TAG, null, "rss")
        parser.nextTag() // channelタグへ移動
        parser.require(XmlPullParser.START_TAG, null, "channel")

        var title = ""
        val items = mutableListOf<RssItem>()

        while (parser.next() != XmlPullParser.END_TAG) {
            when (parser.name) {
                "title" -> title = readItemContent(parser, "title")
                "item" -> items.add(readItem(parser))
                else -> skip(parser)
            }
        }
        return Rss(title = title, items = items)
    }

    internal fun readItem(parser: XmlPullParser): RssItem {
        var title = ""
        var link = ""
        parser.require(XmlPullParser.START_TAG, null, "item")
        while (parser.next() != XmlPullParser.END_TAG) {
            when (parser.name) {
                "title" -> title = readItemContent(parser, "title")
                "link" -> link = readItemContent(parser, "link")
                else -> skip(parser)
            }
        }
        return RssItem(title, link)
    }

    private fun readItemContent(parser: XmlPullParser, tag: String): String {
        parser.require(XmlPullParser.START_TAG, null, tag)
        val content = readText(parser)
        parser.require(XmlPullParser.END_TAG, null, tag)
        return content
    }

    internal fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }

}