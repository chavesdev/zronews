package com.chavesdev.zronews.feed.common.data.remote.datasource

import com.chavesdev.zronews.feed.common.data.remote.models.HighlightListResponse
import retrofit2.Response

interface HighlightsDataSource {
    suspend fun loadHighlights(bearer: String): Response<HighlightListResponse>
}