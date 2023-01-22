package com.github.goutarouh.simplerssreader.core.network.factory

import com.github.goutarouh.simplerssreader.core.network.converter.RssResponseConverter
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class RssConverterFactory private constructor(): Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return RssResponseConverter()
    }

    companion object {
        fun create() = RssConverterFactory()
    }
}