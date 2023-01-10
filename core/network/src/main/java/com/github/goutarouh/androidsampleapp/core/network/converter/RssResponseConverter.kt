package com.github.goutarouh.androidsampleapp.core.network.converter

import com.github.goutarouh.androidsampleapp.core.network.data.rss.Rss
import com.github.goutarouh.androidsampleapp.core.network.parser.RssParser
import okhttp3.ResponseBody
import retrofit2.Converter

class RssResponseConverter: Converter<ResponseBody, Rss> {

    private val parser = RssParser()

    override fun convert(value: ResponseBody): Rss {
        val bodyString = value.string()
        return parser.parse(bodyString.byteInputStream())
    }
}