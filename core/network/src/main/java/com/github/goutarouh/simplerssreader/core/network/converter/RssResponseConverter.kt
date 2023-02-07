package com.github.goutarouh.simplerssreader.core.network.converter

import com.github.goutarouh.simplerssreader.core.network.data.rss.RssApiModel
import com.github.goutarouh.simplerssreader.core.network.parser.JunctionParser
import okhttp3.ResponseBody
import retrofit2.Converter

class RssResponseConverter: Converter<ResponseBody, RssApiModel> {

    private val parser = JunctionParser()

    override fun convert(value: ResponseBody): RssApiModel {
        val bodyString = value.string()
        return parser.parse(bodyString.byteInputStream())
    }
}