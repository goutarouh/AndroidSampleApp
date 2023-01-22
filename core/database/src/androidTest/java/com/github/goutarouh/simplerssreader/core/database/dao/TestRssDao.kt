package com.github.goutarouh.simplerssreader.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.goutarouh.simplerssreader.core.database.AppDatabase
import com.github.goutarouh.simplerssreader.core.database.model.rss.RssEntity
import com.github.goutarouh.simplerssreader.core.database.model.rss.RssFavoriteEntity
import com.github.goutarouh.simplerssreader.core.database.model.rss.RssItemEntity
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
    fun `RssEntityの書き込みと読み込みのテスト`() {
        rssDao.insertRssEntity(RSS_ENTITY)
        val actual = rssDao.getRssEntityList()
        Assert.assertEquals(RSS_ENTITY, actual[0])
    }

    @Test
    @Throws(Exception::class)
    fun `RssItemEntityの書き込みと読み込みのテスト`() {
        rssDao.insertRssEntity(RSS_ENTITY.copy(rssLink = "rssLink"))
        rssDao.insertRssItemEntityList(RSS_ITEM_ENTITY_LIST)
        val actual = rssDao.getRssItemEntityList()
        Assert.assertEquals(RSS_ITEM_ENTITY_LIST.size, actual.size)
    }

    @Test
    @Throws(Exception::class)
    fun `RssFavoriteEntityへの書き込みと読み込みと更新のテスト`() {
        rssDao.insertRssEntity(RSS_ENTITY)
        rssDao.insertRssFavoriteEntity(RSS_FAVORITE_ENTITY)

        kotlin.run {
            val actual = rssDao.getRssFavorite(RSS_FAVORITE_ENTITY.rssLink)
            Assert.assertEquals(RSS_FAVORITE_ENTITY, actual)
        }

        rssDao.updateRssFavoriteEntity(RSS_FAVORITE_ENTITY.copy(isFavorite = true))

        kotlin.run {
            val actual = rssDao.getRssFavorite(RSS_FAVORITE_ENTITY.rssLink)
            Assert.assertEquals(RSS_FAVORITE_ENTITY.copy(isFavorite = true), actual)
        }
    }

    @Test
    @Throws(Exception::class)
    fun `RssFavoriteの存在確認のテスト`() {
        kotlin.run {
            val actual = rssDao.hasRssFavorite(RSS_FAVORITE_ENTITY.rssLink)
            Assert.assertTrue(!actual)
        }
        rssDao.insertRssEntity(RSS_ENTITY)
        rssDao.insertRssFavoriteEntity(RSS_FAVORITE_ENTITY)
        kotlin.run {
            val actual = rssDao.hasRssFavorite(RSS_FAVORITE_ENTITY.rssLink)
            Assert.assertTrue(actual)
        }
    }

//    @Test
//    @Throws(Exception::class)
//    fun `親のRssEntityを削除したら子のRssItemEntityも削除されるか確認`() {
//        rssDao.insertRssEntity(RSS_ENTITY.copy(rssLink = "rssLink"))
//        rssDao.insertRssItemEntityList(RSS_ITEM_ENTITY_LIST.map { it.copy(rssLink = "rssLink") })
//        rssDao.deleteRssEntity("rssLink")
//
//        run {
//            val actual = rssDao.getRssEntityList()
//            Assert.assertEquals(0, actual.size)
//        }
//
//        run {
//            val actual = rssDao.getRssItemEntityList()
//            Assert.assertEquals(0, actual.size)
//        }
//    }

}

private val RSS_ENTITY = RssEntity("rssLink", "title")
private val RSS_ITEM_ENTITY_LIST = List(10) { RssItemEntity("rssLink", "title", "pageLink-$it") }
private val RSS_FAVORITE_ENTITY = RssFavoriteEntity("rssLink", false)