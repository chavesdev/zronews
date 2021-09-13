package com.chavesdev.zronews.news.repo

import com.chavesdev.zronews.common.toDate
import com.chavesdev.zronews.news.data.remote.models.NewsItemResponse
import com.chavesdev.zronews.news.repo.models.NewsItemModel

class NewsMapperImpl: NewsMapper {
    override fun toModel(networkModel: NewsItemResponse): NewsItemModel {
        return NewsItemModel(
            networkModel.title,
            networkModel.description,
            networkModel.content,
            networkModel.author,
            networkModel.publishedAt.toDate(),
            networkModel.url,
            networkModel.imageUrl
        )
    }

    override fun toNetwork(model: NewsItemModel): NewsItemResponse {
        TODO("Not yet implemented")
    }
}