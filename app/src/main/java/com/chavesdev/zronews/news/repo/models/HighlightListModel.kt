package com.chavesdev.zronews.news.repo.models

import com.chavesdev.zronews.common.repo.models.BaseResponseModel
import java.util.*

data class HighlightListModel(
    val highlights: List<HighlightItemModel>?
) : BaseResponseModel()

data class HighlightItemModel(
    val title: String,
    val description: String,
    val content: String,
    val author: String,
    val publishedAt: Date?,
    val url: String,
    val imageUrl: String?
)