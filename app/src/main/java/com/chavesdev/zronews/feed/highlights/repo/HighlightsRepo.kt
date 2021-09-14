package com.chavesdev.zronews.feed.highlights.repo

import com.chavesdev.zronews.feed.highlights.repo.models.HighlightListModel

interface HighlightsRepo {
    suspend fun loadHighlits(token: String) : HighlightListModel?
}