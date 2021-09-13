package com.chavesdev.zronews.news.repo

import android.util.Log
import com.chavesdev.zronews.news.data.remote.datasource.HighlightsDataSource
import com.chavesdev.zronews.news.data.remote.models.HighlightListResponse
import com.chavesdev.zronews.news.repo.models.HighlightListModel
import com.google.gson.Gson

class HighlightsRepoImpl(
    val datasource: HighlightsDataSource,
    private val highlightsMapper: HighlightsMapper,
    val gson: Gson
) : HighlightsRepo {
    override suspend fun loadHighlits(token: String): HighlightListModel? {
        val response = datasource.loadHighlights(token)
        if (response.isSuccessful) {
            response.body()?.let {
                return highlightsMapper.toModel(it)
            } ?: run {
                Log.e("HighlightsRepoImpl", "body is null")
                return null
            }

        } else {
            return when (response.code()) {
                401 -> {
                    val errorBody = response.errorBody()
                    val json: HighlightListResponse =
                        gson.fromJson(errorBody?.string(), HighlightListResponse::class.java)
                    highlightsMapper.toModel(json)
                }
                else -> null
            }
        }
    }
}