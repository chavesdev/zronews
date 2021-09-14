package com.chavesdev.zronews.feed.common.ui

import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel

interface OnItemClickListener {
    fun onNewsItemClicked(newsItemModel: NewsItemModel)
}