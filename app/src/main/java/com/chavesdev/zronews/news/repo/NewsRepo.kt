package com.chavesdev.zronews.news.repo

import androidx.paging.PagingData
import com.chavesdev.zronews.news.repo.models.NewsItemModel
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
    fun loadNews() : Flow<PagingData<NewsItemModel>>
}