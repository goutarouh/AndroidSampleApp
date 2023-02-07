package com.github.goutarouh.simplerssreader.core.network.parser

import android.util.Xml
import com.github.goutarouh.simplerssreader.core.network.data.rss.RssApiModel
import com.github.goutarouh.simplerssreader.core.util.exception.ParseException
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream

class JunctionParser {

    internal fun parse(input: InputStream): RssApiModel {
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(input, null)
        parser.nextTag()

        return when (parser.name) {
            "rss" -> {
                RssParser().readRss(parser)
            }
            "feed" -> {
                AtomParser().readFeed(parser)
            }
            else -> {
                throw ParseException("")
            }
        }
    }

}