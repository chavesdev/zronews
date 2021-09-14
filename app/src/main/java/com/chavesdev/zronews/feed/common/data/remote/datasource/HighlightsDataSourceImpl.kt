package com.chavesdev.zronews.feed.common.data.remote.datasource

import com.chavesdev.zronews.feed.common.data.remote.service.NewsApi

class HighlightsDataSourceImpl(private val newsApi: NewsApi) : HighlightsDataSource {
    override suspend fun loadHighlights(bearer: String) = newsApi.getHighlights(bearer)
}