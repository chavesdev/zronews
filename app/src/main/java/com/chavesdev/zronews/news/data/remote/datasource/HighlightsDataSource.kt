package com.chavesdev.zronews.news.data.remote.datasource

import com.chavesdev.zronews.news.data.remote.models.HighlightListResponse
import retrofit2.Response

interface HighlightsDataSource {
    suspend fun loadHighlights(bearer: String): Response<HighlightListResponse>
}