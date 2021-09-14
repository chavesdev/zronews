package com.chavesdev.zronews.feed.highlights.repo

import com.chavesdev.zronews.common.toDate
import com.chavesdev.zronews.feed.common.data.remote.models.HighlightItem
import com.chavesdev.zronews.feed.common.data.remote.models.HighlightListResponse
import com.chavesdev.zronews.feed.highlights.repo.models.HighlightListModel
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel

class HighlightsMapperImpl: HighlightsMapper {
    override fun toModel(networkModel: HighlightListResponse): HighlightListModel {
        return HighlightListModel(transform(networkModel.data)).apply {
            code = networkModel.code
            message = networkModel.message
        }
    }

    override fun toNetwork(model: HighlightListModel): HighlightListResponse {
        TODO("Not yet implemented")
    }

    private fun transform(list : List<HighlightItem>?) : List<NewsItemModel>? {
        return list?.map {
            NewsItemModel(
                it.title,
                it.description,
                it.content,
                it.author,
                it.publishedAt.toDate(),
                it.url,
                it.imageUrl
            )
        }
    }
}