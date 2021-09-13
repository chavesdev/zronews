package com.chavesdev.zronews.news.data.remote.service

import com.chavesdev.zronews.news.data.remote.models.HighlightListResponse
import com.chavesdev.zronews.news.data.remote.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {

    @GET("/v1/client/news/highlights")
    suspend fun getHighlights(@Header("Authorization") bearer: String): Response<HighlightListResponse>

    @GET("/v1/client/news")
    suspend fun getNews(
        @Header("Authorization") bearer: String,
        @Query("current_page") currentPage: Int? = 1,
        @Query("per_page") perPage: Int? = 20,
        @Query("publishet_at") publishedAt: String? = "YYYY-mm-dd"
    ): NewsResponse
}