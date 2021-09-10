package com.chavesdev.zronews.news.data.remote.datasource

import com.chavesdev.zronews.news.data.remote.service.NewsApi

class NewsDataSourceImpl(private val newsApi: NewsApi) : NewsDataSource {
    override suspend fun loadHighlights(bearer: String) = newsApi.getHighlights(bearer)
}