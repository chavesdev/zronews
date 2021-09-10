package com.chavesdev.zronews.news.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chavesdev.zronews.auth.AuthManager
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.news.repo.HighlightsRepo
import com.chavesdev.zronews.news.repo.models.HighlightItemModel
import com.chavesdev.zronews.news.repo.models.HighlightListModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

class HighlightListViewModelTest {

    private val authManager: AuthManager = mockk()

    private val highlightsRepo: HighlightsRepo = mockk()

    private lateinit var highlightListViewModel: HighlightListViewModel

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @Test
    fun checkViewModelStartsReady() {
        highlightListViewModel = HighlightListViewModel(authManager, highlightsRepo, Dispatchers.Unconfined)
        assertEquals(LoadState.READY, highlightListViewModel.highligthsListState.value)
    }

    @Test
    fun checkErrorResponse() {
        //given
        coEvery { authManager.getBearerToken() } returns ""
        coEvery { highlightsRepo.loadHighlits(any()) } returns null

        //when
        highlightListViewModel = HighlightListViewModel(authManager, highlightsRepo, Dispatchers.Unconfined)
        highlightListViewModel.loadHighlights()

        //then
        assert(highlightListViewModel.highligthsListState.value is LoadState.ERROR)
    }

    @Test
    fun checkSuccessResponse() {
        //given
        coEvery { authManager.getBearerToken() } returns ""
        coEvery { highlightsRepo.loadHighlits(any()) } returns successResponse

        //when
        highlightListViewModel = HighlightListViewModel(authManager, highlightsRepo, Dispatchers.Unconfined)
        highlightListViewModel.loadHighlights()

        //then
        assert(highlightListViewModel.highligthsListState.value is LoadState.SUCCESS)
    }

    companion object{
        val successResponse = HighlightListModel(listOf(HighlightItemModel("","","", "", Date(), "", "")))
    }
}