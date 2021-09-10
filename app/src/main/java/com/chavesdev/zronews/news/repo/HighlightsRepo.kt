package com.chavesdev.zronews.news.repo

import com.chavesdev.zronews.news.repo.models.HighlightListModel

interface HighlightsRepo {
    suspend fun loadHighlits(token: String) : HighlightListModel?
}