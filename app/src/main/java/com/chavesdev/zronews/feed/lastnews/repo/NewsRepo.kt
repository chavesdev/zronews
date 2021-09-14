package com.chavesdev.zronews.feed.lastnews.repo

import androidx.paging.PagingData
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
    fun loadNews() : Flow<PagingData<NewsItemModel>>
}