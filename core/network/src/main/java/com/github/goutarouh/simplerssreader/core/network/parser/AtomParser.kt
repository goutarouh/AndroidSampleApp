package com.github.goutarouh.simplerssreader.core.network.parser

import com.github.goutarouh.simplerssreader.core.network.data.rss.RssApiModel
import com.github.goutarouh.simplerssreader.core.network.data.rss.RssItemApiModel
import org.xmlpull.v1.XmlPullParser

class AtomParser {

    internal fun readFeed(parser: XmlPullParser): RssApiModel {
        parser.require(XmlPullParser.START_TAG, null, "feed")

        var title = ""
        val items = mutableListOf<RssItemApiModel>()

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            println(parser.name)
            when (parser.name) {
                "title" -> title = parser.readTagContent("title")
                "entry" -> items.add(readEntry(parser))
                else -> parser.skip()
            }
        }
        return RssApiModel(title = title, items = items)
    }

    internal fun readEntry(parser: XmlPullParser): RssItemApiModel {
        var title = ""
        var link = ""
        parser.require(XmlPullParser.START_TAG, null, "entry")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "title" -> title = parser.readTagContent("title")
                "link" -> {
                    link = parser.getAttributeValue(null, "href")
                    parser.nextTag()
                }
                else -> parser.skip()
            }
        }
        return RssItemApiModel(title, link)
    }
}