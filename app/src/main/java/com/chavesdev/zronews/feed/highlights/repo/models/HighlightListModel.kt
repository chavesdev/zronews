package com.chavesdev.zronews.feed.highlights.repo.models

import com.chavesdev.zronews.common.repo.models.BaseResponseModel
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel

data class HighlightListModel(
    val highlights: List<NewsItemModel>?
) : BaseResponseModel()