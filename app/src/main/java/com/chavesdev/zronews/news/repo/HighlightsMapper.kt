package com.chavesdev.zronews.news.repo

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.news.data.remote.models.HighlightListResponse
import com.chavesdev.zronews.news.repo.models.HighlightListModel

interface HighlightsMapper: NetworkMapper<HighlightListModel, HighlightListResponse>