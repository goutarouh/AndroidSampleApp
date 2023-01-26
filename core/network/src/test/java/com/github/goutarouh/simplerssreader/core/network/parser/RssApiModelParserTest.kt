package com.github.goutarouh.simplerssreader.core.network.parser


import android.util.Xml
import com.github.goutarouh.simplerssreader.core.network.data.rss.RssApiModel
import com.github.goutarouh.simplerssreader.core.network.data.rss.RssItemApiModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.xmlpull.v1.XmlPullParser

@RunWith(RobolectricTestRunner::class)
internal class RssParserTest {

    lateinit var rssParser: RssParser

    @Before
    fun setup() {
        rssParser = RssParser()
    }

    // TODO 全体が取得できるかどうか
    @Test
    fun readRss() {
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(RSS_ALL_XML.byteInputStream(), null)
        parser.nextTag()

        val actual = rssParser.readRss(parser)
        Assert.assertEquals(RSS_ALL.title, actual.title)
        Assert.assertEquals(RSS_ALL.items.size, actual.items.size)
    }

    @Test
    fun readItem() {
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(RSS_ITEM_XML.byteInputStream(), null)
        parser.nextTag()

        val actual = rssParser.readItem(parser)
        Assert.assertEquals(RSS_ITEM, actual)
    }

    @Test
    fun readText() {
        val parser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(RSS_TEXT_XML.byteInputStream(), null)
        parser.nextTag()

        val actual = rssParser.readText(parser)
        Assert.assertEquals(RSS_TEXT, actual)
    }


}

private val RSS_ALL = RssApiModel(
    title = "Zennの「Android」のフィード",
    imageLink = "",
    items = List(2) { RssItemApiModel() },
)
private val RSS_ALL_XML = """
    <rss xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:content="http://purl.org/rss/1.0/modules/content/" xmlns:atom="http://www.w3.org/2005/Atom" version="2.0">
    <channel>
    <title>
    <![CDATA[${RSS_ALL.title}]]>
    </title>
    <description>
    <![CDATA[ Zennのトピック「Android」のRSSフィードです ]]>
    </description>
    <link>https://zenn.dev/topics/android</link>
    <image>
    <url>https://storage.googleapis.com/zenn-user-upload/topics/e5bf169a2e.png</url>
    <title>Zennの「Android」のフィード</title>
    <link>https://zenn.dev/topics/android</link>
    </image>
    <generator>zenn.dev</generator>
    <lastBuildDate>Tue, 10 Jan 2023 01:53:03 GMT</lastBuildDate>
    <atom:link href="https://zenn.dev/topics/android/feed" rel="self" type="application/rss+xml"/>
    <language>
    <![CDATA[ ja ]]>
    </language>
    <item>
    <title>
    <![CDATA[ 【Flutter】codemagic.yamlでFirebase App Distributionにベータ配布する (Android版) ]]>
    </title>
    <description>
    <![CDATA[ Codemagic 今回は既存のFlutterアプリのAndroidのみをCodemagicのダッシュボードは使わず、codemagic.yaml を使用してFirebase App Distributionへベータ配布するまでをやってみたいと思います。 既にFlutterアプリが特定のGithubのリポジトリに作成されている前提になります。 Sign up まずはCodemagicに Github アカウント でSign upします。 「Github」を選択します。 次に表示された内容に問題なければ「Authorize Codemagic CI/CD」を選択します。 ダ... ]]>
    </description>
    <link>https://zenn.dev/slowhand/articles/b5aa0410765489</link>
    <guid isPermaLink="true">https://zenn.dev/slowhand/articles/b5aa0410765489</guid>
    <pubDate>Sat, 07 Jan 2023 16:22:54 GMT</pubDate>
    <enclosure url="https://res.cloudinary.com/zenn/image/upload/s--JxxURDPn--/co_rgb:222%2Cg_south_west%2Cl_text:notosansjp-medium.otf_37_bold:slowhand%2Cx_203%2Cy_98/c_fit%2Cco_rgb:222%2Cg_north_west%2Cl_text:notosansjp-medium.otf_57_bold:%25E3%2580%2590Flutter%25E3%2580%2591codemagic.yaml%25E3%2581%25A7Firebase%2520App%2520Distribution%25E3%2581%25AB%25E3%2583%2599%25E3%2583%25BC%25E3%2582%25BF%25E9%2585%258D%25E5%25B8%2583%25E3%2581%2599%25E3%2582%258B%2520%2528Android%25E7%2589%2588%2529%2Cw_1010%2Cx_90%2Cy_100/g_south_west%2Ch_90%2Cl_fetch:aHR0cHM6Ly9yZXMuY2xvdWRpbmFyeS5jb20vemVubi9pbWFnZS9mZXRjaC9zLS1jRDNVSUVLSy0tL2NfbGltaXQlMkNmX2F1dG8lMkNmbF9wcm9ncmVzc2l2ZSUyQ3FfYXV0byUyQ3dfNzAvaHR0cHM6Ly9zdG9yYWdlLmdvb2dsZWFwaXMuY29tL3plbm4tdXNlci11cGxvYWQvYXZhdGFyLzEwY2YzMGUzMDAuanBlZw==%2Cr_max%2Cw_90%2Cx_87%2Cy_72/v1627274783/default/og-base_z4sxah.png" length="0" type="image/png"/>
    <dc:creator>slowhand</dc:creator>
    </item>
    <item>
    <title>
    <![CDATA[ BitriseでAndroidライブラリモジュール(AAR)のAndroidTestを実行する ]]>
    </title>
    <description>
    <![CDATA[ ニッチな需要ではあるが、ライブラリをSDKとして社外に渡す機会があり、BitriseではAndroidライブラリ（AAR）でAndroiTestを実行することができず困っていた。 エラーログと闘った結果、Issueコメントにあったワークアラウンド対応で実行することができたのでメモしておく。 先に結論 Bitriseはライブラリモジュールの【Virtual Device Testing】を想定していないので、【Virtual Device Testing】の【App Path】には適当なダミーAPKを指定する 参考URL: https://github.com/bitrise-step... ]]>
    </description>
    <link>https://zenn.dev/musikirin/articles/6f1a3c4fa12962</link>
    <guid isPermaLink="true">https://zenn.dev/musikirin/articles/6f1a3c4fa12962</guid>
    <pubDate>Thu, 05 Jan 2023 14:36:16 GMT</pubDate>
    <enclosure url="https://res.cloudinary.com/zenn/image/upload/s--pvpOEJ4L--/co_rgb:222%2Cg_south_west%2Cl_text:notosansjp-medium.otf_37_bold:%25E3%2581%258D%25E3%2582%258A%25E3%2582%2593%2Cx_203%2Cy_98/c_fit%2Cco_rgb:222%2Cg_north_west%2Cl_text:notosansjp-medium.otf_65_bold:Bitrise%25E3%2581%25A7Android%25E3%2583%25A9%25E3%2582%25A4%25E3%2583%2596%25E3%2583%25A9%25E3%2583%25AA%25E3%2583%25A2%25E3%2582%25B8%25E3%2583%25A5%25E3%2583%25BC%25E3%2583%25AB%2528AAR%2529%25E3%2581%25AEAndroidTest%25E3%2582%2592%25E5%25AE%259F%25E8%25A1%258C%25E3%2581%2599%25E3%2582%258B%2Cw_1010%2Cx_90%2Cy_100/g_south_west%2Ch_90%2Cl_fetch:aHR0cHM6Ly9yZXMuY2xvdWRpbmFyeS5jb20vemVubi9pbWFnZS9mZXRjaC9zLS0zYlY3eVdqMC0tL2NfbGltaXQlMkNmX2F1dG8lMkNmbF9wcm9ncmVzc2l2ZSUyQ3FfYXV0byUyQ3dfNzAvaHR0cHM6Ly9zdG9yYWdlLmdvb2dsZWFwaXMuY29tL3plbm4tdXNlci11cGxvYWQvYXZhdGFyLzA3NDcwYjVlZDIuanBlZw==%2Cr_max%2Cw_90%2Cx_87%2Cy_72/v1627274783/default/og-base_z4sxah.png" length="0" type="image/png"/>
    <dc:creator>きりん</dc:creator>
    </item>
    </channel>
    </rss>
""".trimIndent().replace("\n", "")


private val RSS_ITEM = RssItemApiModel("App モジュールの BuildConfig をプロジェクト全体で使う", "https://zenn.dev/gottie/articles/88668d2571dc37")
private val RSS_ITEM_XML = """
    <item>
    <title>
    <![CDATA[${RSS_ITEM.title}]]>
    </title>
    <description>
    <![CDATA[ はじめに マルチモジュールのアプリで App モジュールの BuildConfig などの設定をプロジェクト全体で使用する方法のメモです。 アプリ構成 app モジュールが各機能モジュールに依存します。 各機能モジュールは repository モジュールに依存します。 repository モジュールは databse,network モジュールに依存します。 実装 resource モジュールを作成、 AppConfig データクラスを作成する 設定値を保持するクラスを持つモジュールとして、resources を作成します。 その中に下記のような AppConf... ]]>
    </description>
    <link>${RSS_ITEM.link}</link>
    <guid isPermaLink="true">https://zenn.dev/gottie/articles/88668d2571dc37</guid>
    <pubDate>Mon, 09 Jan 2023 11:58:56 GMT</pubDate>
    <enclosure url="https://res.cloudinary.com/zenn/image/upload/s--GImikI5m--/co_rgb:222%2Cg_south_west%2Cl_text:notosansjp-medium.otf_37_bold:gottie%2Cx_203%2Cy_98/c_fit%2Cco_rgb:222%2Cg_north_west%2Cl_text:notosansjp-medium.otf_60_bold:App%2520%25E3%2583%25A2%25E3%2582%25B8%25E3%2583%25A5%25E3%2583%25BC%25E3%2583%25AB%25E3%2581%25AE%2520BuildConfig%2520%25E3%2582%2592%25E3%2583%2597%25E3%2583%25AD%25E3%2582%25B8%25E3%2582%25A7%25E3%2582%25AF%25E3%2583%2588%25E5%2585%25A8%25E4%25BD%2593%25E3%2581%25A7%25E4%25BD%25BF%25E3%2581%2586%2520%2528android%252C%2520hilt%2529%2Cw_1010%2Cx_90%2Cy_100/g_south_west%2Ch_90%2Cl_fetch:aHR0cHM6Ly9yZXMuY2xvdWRpbmFyeS5jb20vemVubi9pbWFnZS9mZXRjaC9zLS1nU2FrSVhNMC0tL2NfbGltaXQlMkNmX2F1dG8lMkNmbF9wcm9ncmVzc2l2ZSUyQ3FfYXV0byUyQ3dfNzAvaHR0cHM6Ly9zdG9yYWdlLmdvb2dsZWFwaXMuY29tL3plbm4tdXNlci11cGxvYWQvYXZhdGFyL2RjMGY3YzMwYjEuanBlZw==%2Cr_max%2Cw_90%2Cx_87%2Cy_72/v1627274783/default/og-base_z4sxah.png" length="0" type="image/png"/>
    <dc:creator>gottie</dc:creator>
    </item>
""".trimIndent().replace("\n", "")


private const val RSS_TEXT = "Maestroで快適なiOS/AndroidのUIテストを！"
private val RSS_TEXT_XML = """
    <title>
    <![CDATA[${RSS_TEXT}]]>
    </title>
""".trimIndent().replace("\n", "")
