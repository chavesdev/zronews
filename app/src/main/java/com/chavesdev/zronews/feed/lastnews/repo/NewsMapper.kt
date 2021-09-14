package com.chavesdev.zronews.feed.lastnews.repo

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.feed.common.data.remote.models.NewsItemResponse
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel

interface NewsMapper: NetworkMapper<NewsItemModel, NewsItemResponse> {
}