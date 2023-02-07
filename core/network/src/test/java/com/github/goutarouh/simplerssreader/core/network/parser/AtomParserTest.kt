package com.github.goutarouh.simplerssreader.core.network.parser

import android.util.Xml
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.xmlpull.v1.XmlPullParser


@RunWith(RobolectricTestRunner::class)
internal class AtomParserTest {

    lateinit var atomParser: AtomParser

    @Before
    fun setup() {
        atomParser = AtomParser()
    }

    @Test
    fun readFeedTest() {
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(ATOM_XML.byteInputStream(), null)
        parser.nextTag()


        val actual = atomParser.readFeed(parser)
        Assert.assertEquals("Qiita - 人気の記事", actual.title)
    }

}

private val ATOM_XML = """
    <?xml version="1.0" encoding="UTF-8"?>
    <feed xml:lang="ja-JP" xmlns="http://www.w3.org/2005/Atom">
      <id>tag:qiita.com,2005:/popular-items/feed</id>
      <link rel="alternate" type="text/html" href="https://qiita.com/popular-items"/>
      <link rel="self" type="application/atom+xml" href="https://qiita.com/popular-items/feed"/>
      <title>Qiita - 人気の記事</title>
      <updated>2023-02-07T17:00:00+09:00</updated>
      <entry>
        <id>tag:qiita.com,2005:PublicArticle/1680311</id>
        <published>2023-02-05T19:36:38+09:00</published>
        <updated>2023-02-05T19:36:38+09:00</updated>
        <link rel="alternate" type="text/html" href="https://qiita.com/riita10069/items/292c90b008e3714ee88b?utm_campaign=popular_items&amp;utm_medium=feed&amp;utm_source=popular_items"/>
        <title>Kubernetesに腰を据えて入門する方向けのロードマップ</title>
        <content type="html">背景
    最近、「コンテナはもうわかってきたので、これからKubernetesについて腰を据えて勉強したいが、どのように勉強すればいいかわからない」という相談をいただくことがすごく多くなった。
    必要に応…</content>
        <author>
          <name>riita10069</name>
        </author>
      </entry>
      <entry>
        <id>tag:qiita.com,2005:PublicArticle/1680416</id>
        <published>2023-02-06T00:25:52+09:00</published>
        <updated>2023-02-07T15:37:53+09:00</updated>
        <link rel="alternate" type="text/html" href="https://qiita.com/gpsnmeajp/items/77eee9535fb1a092e286?utm_campaign=popular_items&amp;utm_medium=feed&amp;utm_source=popular_items"/>
        <title>Nostrプロトコル(damus)を触ってみた</title>
        <content type="html">はじめに
    Twitterの動乱に巻き込まれている皆様、いかがお過ごしでしょうか。
    私も例外なく巻き込まれており、特にAPI利用していたアプリケーションを停止することになって非常に残念です。
    そこでT…</content>
        <author>
          <name>gpsnmeajp</name>
        </author>
      </entry>
    </feed>
""".trimIndent().replace("\n", "")