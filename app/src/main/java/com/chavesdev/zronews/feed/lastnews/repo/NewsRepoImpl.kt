package com.chavesdev.zronews.feed.lastnews.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chavesdev.zronews.auth.AuthManager
import com.chavesdev.zronews.feed.common.data.remote.datasource.NewsDataSource
import com.chavesdev.zronews.feed.common.data.remote.service.NewsApi
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel
import kotlinx.coroutines.flow.Flow

class NewsRepoImpl(
    private val newsApi: NewsApi,
    val authManager: AuthManager,
    private val newsMapper: NewsMapper
) : NewsRepo {
    override fun loadNews(): Flow<PagingData<NewsItemModel>> {
        return Pager(
            config = PagingConfig(20, enablePlaceholders = false),
            pagingSourceFactory = { NewsDataSource(newsApi, authManager, newsMapper) }
        ).flow
    }
}