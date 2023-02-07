package com.github.goutarouh.simplerssreader.core.network.parser

import android.util.Xml
import com.github.goutarouh.simplerssreader.core.network.data.rss.RssApiModel
import com.github.goutarouh.simplerssreader.core.network.data.rss.RssItemApiModel
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

/**
 * 参考：https://developer.android.com/training/basics/network-ops/xml?hl=ja#consume
 */
class RssParser {

    internal fun readRss(parser: XmlPullParser): RssApiModel {
        parser.require(XmlPullParser.START_TAG, null, "rss")
        parser.nextTag() // channelタグへ移動
        parser.require(XmlPullParser.START_TAG, null, "channel")

        var title = ""
        var imageLink = ""
        val items = mutableListOf<RssItemApiModel>()

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "title" -> title = parser.readTagContent("title")
                "image" -> imageLink = readImage(parser)
                "item" -> items.add(readItem(parser))
                else -> parser.skip()
            }
        }
        return RssApiModel(title = title, imageLink = imageLink, items = items)
    }

    internal fun readImage(parser: XmlPullParser): String {
        var imageLink  = ""
        parser.require(XmlPullParser.START_TAG, null, "image")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            if (parser.name == "url") {
                imageLink =  parser.readTagContent("url")
            } else {
                parser.skip()
            }
        }
        return imageLink
    }

    internal fun readItem(parser: XmlPullParser): RssItemApiModel {
        var title = ""
        var link = ""
        parser.require(XmlPullParser.START_TAG, null, "item")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "title" -> title = parser.readTagContent("title")
                "link" -> link = parser.readTagContent("link")
                else -> parser.skip()
            }
        }
        return RssItemApiModel(title, link)
    }
}