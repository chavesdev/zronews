package com.chavesdev.zronews.news.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.chavesdev.zronews.news.repo.NewsRepo
import com.chavesdev.zronews.news.repo.models.NewsItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepo: NewsRepo) : ViewModel() {
    lateinit var newsList : Flow<PagingData<NewsItemModel>>

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch {
            try {
                val response = newsRepo.loadNews()
                newsList = response
            } catch (ex: Exception) {
                Log.e("NewsViewModel", ex.message ?: "Deu ruim")
            }

        }
    }
}