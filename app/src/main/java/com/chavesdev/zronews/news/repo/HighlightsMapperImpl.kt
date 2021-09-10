package com.chavesdev.zronews.news.repo

import com.chavesdev.zronews.common.toDate
import com.chavesdev.zronews.news.data.remote.models.HighlightItem
import com.chavesdev.zronews.news.data.remote.models.HighlightListResponse
import com.chavesdev.zronews.news.repo.models.HighlightItemModel
import com.chavesdev.zronews.news.repo.models.HighlightListModel

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

    private fun transform(list : List<HighlightItem>?) : List<HighlightItemModel>? {
        return list?.map {
            HighlightItemModel(
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