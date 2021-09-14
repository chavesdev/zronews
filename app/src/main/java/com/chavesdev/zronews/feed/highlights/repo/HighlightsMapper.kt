package com.chavesdev.zronews.feed.highlights.repo

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.feed.common.data.remote.models.HighlightListResponse
import com.chavesdev.zronews.feed.highlights.repo.models.HighlightListModel

interface HighlightsMapper: NetworkMapper<HighlightListModel, HighlightListResponse>