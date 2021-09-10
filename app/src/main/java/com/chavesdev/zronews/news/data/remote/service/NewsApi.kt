package com.chavesdev.zronews.news.data.remote.service

import com.chavesdev.zronews.news.data.remote.models.HighlightListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NewsApi {

    @GET("/v1/client/news/highlights")
    suspend fun getHighlights(@Header("Authorization") bearer: String): Response<HighlightListResponse>
}