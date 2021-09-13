package com.chavesdev.zronews.news.repo.models

import java.util.*

data class NewsItemModel(
    val title: String,
    val description: String,
    val content: String,
    val author: String,
    val publishedAt: Date?,
    val url: String,
    val imageUrl: String?
)