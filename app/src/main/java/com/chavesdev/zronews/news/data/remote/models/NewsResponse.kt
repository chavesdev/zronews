package com.chavesdev.zronews.news.data.remote.models

import com.chavesdev.zronews.common.data.remote.responses.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(
    @Expose
    @SerializedName("pagination")
    val pagination: Pagination,
    @Expose
    @SerializedName("data")
    val news: List<NewsItemResponse> = ArrayList()
) : BaseResponse()

data class Pagination(
    @Expose
    @SerializedName("current_page")
    val currentPage: Int,
    @Expose
    @SerializedName("per_page")
    val perPage: Int,
    @Expose
    @SerializedName("total_pages")
    val totalPages: Int,
    @Expose
    @SerializedName("total_items")
    val totalItems: Int
) : Serializable

data class NewsItemResponse(
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("content")
    val content: String,
    @Expose
    @SerializedName("author")
    val author: String,
    @Expose
    @SerializedName("published_at")
    val publishedAt: String,
    @Expose
    @SerializedName("url")
    val url: String,
    @Expose
    @SerializedName("image_url")
    var imageUrl: String? = null
) : Serializable
