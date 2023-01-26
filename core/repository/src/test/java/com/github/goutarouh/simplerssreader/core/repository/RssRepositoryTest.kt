package com.github.goutarouh.simplerssreader.core.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.github.goutarouh.simplerssreader.core.database.TransactionProcessExecutor
import com.github.goutarouh.simplerssreader.core.database.dao.RssDao
import com.github.goutarouh.simplerssreader.core.database.model.rss.RssItemEntity
import com.github.goutarouh.simplerssreader.core.database.model.rss.RssWrapperData
import com.github.goutarouh.simplerssreader.core.network.service.RssService
import com.github.goutarouh.simplerssreader.core.util.data.Result
import com.github.goutarouh.simplerssreader.core.repository.model.rss.Rss
import com.github.goutarouh.simplerssreader.core.repository.model.rss.RssItem
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class RssRepositoryTest {

    @MockK lateinit var rssDao: RssDao
    @MockK lateinit var rssService: RssService
    @MockK lateinit var transactionProcessExecutor: TransactionProcessExecutor
    lateinit var rssRepository: RssRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        val context = ApplicationProvider.getApplicationContext<Context>()
        rssRepository = RssRepositoryImpl(context, transactionProcessExecutor, rssService, rssDao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDeleteAndUnregisterRss() {
        coEvery {
            rssDao.deleteRssEntity(any())
        } returns Unit

        val spyRssRepository = spyk(rssRepository)
        coEvery {
            spyRssRepository.unRegisterWorker("")
        } returns true

        runTest {
            spyRssRepository.deleteAndUnregisterRss("")
        }

        coVerify {
            spyRssRepository.unRegisterWorker("")
            rssDao.deleteRssEntity("")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `testUpdateRssAndCheckNewItemCount_全て更新の場合1`() {
        val old = RssWrapperData(
            items = listOf()
        )
        val new = Rss(
            items = List(10) { RssItem(pageLink = "new-${it}") }
        )
        coEvery { rssDao.getRssWrapperData(any()) } returns old

        val spyRssRepository = spyk(rssRepository, recordPrivateCalls = true)
        coEvery { spyRssRepository.updateRss(any(), any()) } returns Result.Success(new)

        runTest {
            val count = spyRssRepository.updateRssAndCheckNewItemCount("")

            Assert.assertEquals(10, count)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `testUpdateRssAndCheckNewItemCount_全て更新の場合2`() {

        val old = RssWrapperData(
            items = List(10) { RssItemEntity(order = it, pageLink = "old-${it}") }
        )
        val new = Rss(
            items = List(10) { RssItem(pageLink = "new-${it}") }
        )

        coEvery { rssDao.getRssWrapperData(any()) } returns old

        val spyRssRepository = spyk(rssRepository, recordPrivateCalls = true)
        coEvery { spyRssRepository.updateRss(any(), any()) } returns Result.Success(new)

        runTest {
            val count = spyRssRepository.updateRssAndCheckNewItemCount("")
            Assert.assertEquals(count, 10)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `testUpdateRssAndCheckNewItemCount_5個更新の場合`() {
        val old = RssWrapperData(
            items = List(10) { RssItemEntity(order = it, pageLink = "page-link-${it + 5}") }
        )
        val new = Rss(
            items = List(10) { RssItem(pageLink = "page-link-$it") }
        )

        coEvery { rssDao.getRssWrapperData(any()) } returns old
        val spyRssRepository = spyk(rssRepository, recordPrivateCalls = true)
        coEvery { spyRssRepository.updateRss(any(), any()) } returns Result.Success(new)

        runTest {
            val count = spyRssRepository.updateRssAndCheckNewItemCount("")
            Assert.assertEquals(count, 5)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `testUpdateRssAndCheckNewItemCount_更新がない場合`() {
        val old = RssWrapperData(
            items = List(10) { RssItemEntity(order = it, pageLink = "page-link-${it}") }
        )
        val new = Rss(
            items = List(10) { RssItem(pageLink = "page-link-$it") }
        )
        coEvery { rssDao.getRssWrapperData(any()) } returns old
        val spyRssRepository = spyk(rssRepository, recordPrivateCalls = true)
        coEvery { spyRssRepository.updateRss(any(), any()) } returns Result.Success(new)

        runTest {
            val count = spyRssRepository.updateRssAndCheckNewItemCount("")
            Assert.assertEquals(count, 0)
        }
    }
}