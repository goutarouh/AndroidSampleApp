package com.github.goutarouh.androidsampleapp.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.goutarouh.androidsampleapp.core.database.AppDatabase
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssDB
import com.github.goutarouh.androidsampleapp.core.database.model.rss.RssItemDB
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TestRssDao {
    private lateinit var rssDao: RssDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        rssDao = db.rssDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun `RssDbの書き込みと読み込みのテスト`() {
        rssDao.insertRssDb(RSS_DB)
        val actual = rssDao.getRssDbALL()
        Assert.assertEquals(RSS_DB, actual[0])
    }

    @Test
    @Throws(Exception::class)
    fun `RssItemDBの書き込みと読み込みのテスト`() {
        rssDao.insertRssItemDbs(RSS_ITEM_DB_LIST)
        val actual = rssDao.getRssItemDbAll()
        Assert.assertEquals(RSS_ITEM_DB_LIST.size, actual.size)
    }

    @Test
    @Throws(Exception::class)
    fun `親のRssDBを削除したら子のRssItemDBも削除されるか確認`() {
        rssDao.insertRssDb(RSS_DB.copy(title = "title"))
        rssDao.insertRssItemDbs(RSS_ITEM_DB_LIST.map { it.copy(parentTitle = "title") })
        rssDao.deleteRssDb("title")

        run {
            val actual = rssDao.getRssDbALL()
            Assert.assertEquals(0, actual.size)
        }

        run {
            val actual = rssDao.getRssItemDbAll()
            Assert.assertEquals(0, actual.size)
        }
    }
}

private val RSS_DB = RssDB(title = "")
private val RSS_ITEM_DB_LIST = List(10) { RssItemDB(title = "", link = "") }