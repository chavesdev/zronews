package com.chavesdev.zronews.feed.common.data.remote.models

import com.chavesdev.zronews.common.data.remote.responses.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HighlightListResponse(
    @Expose
    @SerializedName("data")
    val data: List<HighlightItem>?
) : BaseResponse()

data class HighlightItem(
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
