package com.chavesdev.zronews.news.repo

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.news.data.remote.models.NewsItemResponse
import com.chavesdev.zronews.news.repo.models.NewsItemModel

interface NewsMapper: NetworkMapper<NewsItemModel, NewsItemResponse> {
}