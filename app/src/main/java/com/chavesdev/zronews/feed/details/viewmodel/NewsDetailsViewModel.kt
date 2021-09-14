package com.chavesdev.zronews.feed.details.viewmodel

import androidx.lifecycle.ViewModel
import com.chavesdev.zronews.common.format
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel
import java.util.*

class NewsDetailsViewModel(
    val title: String,
    val description: String,
    val content: String,
    private val author: String,
    private val publishedAt: Date?,
    val url: String,
    val imageUrl: String?
) : ViewModel() {

    val subtitle: String
        get() {
            return "${publishedAt?.format()} por $author"
        }

    companion object {
        fun fromNewsItemModel(itemModel: NewsItemModel): NewsDetailsViewModel {
            return NewsDetailsViewModel(
                itemModel.title,
                itemModel.description,
                itemModel.content,
                itemModel.author,
                itemModel.publishedAt,
                itemModel.url,
                itemModel.imageUrl
            )
        }
    }
}