package com.chavesdev.zronews.news.data.remote.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chavesdev.zronews.auth.AuthManager
import com.chavesdev.zronews.common.util.ERRORCODE
import com.chavesdev.zronews.news.data.remote.models.NewsItemResponse
import com.chavesdev.zronews.news.data.remote.service.NewsApi
import com.chavesdev.zronews.news.repo.NewsMapper
import com.chavesdev.zronews.news.repo.models.NewsItemModel

class NewsDataSource(private val newsApi: NewsApi, private val authManager: AuthManager, private val newsMapper: NewsMapper) :
    PagingSource<Int, NewsItemModel>() {

    override fun getRefreshKey(state: PagingState<Int, NewsItemModel>): Int = state.anchorPosition ?: 1

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, NewsItemModel> {
        val currentPage = params.key ?: STARTING_PAGE

        return try {
            val token = authManager.getBearerToken()
            if (token != null) {
                val response = newsApi.getNews(token, currentPage)
                val data = response.news.map {
                    newsMapper.toModel(it)
                }

                var nextPage: Int? = null
                with(response.pagination){
                    if (currentPage < totalPages) {
                        nextPage = currentPage + 1
                    }
                }

                LoadResult.Page(
                    data = data,
                    prevKey = null,
                    nextKey = nextPage
                )
            } else {
                LoadResult.Error(Exception(ERRORCODE.OFFLINE.name))
            }

        } catch (e: Exception) {
            Log.e("NewsDataSource", e.message ?: "Deu ruim")
            LoadResult.Error(e)
        }
    }

    companion object{
        const val STARTING_PAGE = 1
    }
}