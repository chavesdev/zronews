package com.chavesdev.zronews.news.viewmodel

import androidx.lifecycle.ViewModel

class NewsItemListViewModel(
    val title: String,
    val description: String,
    val imageUrl: String
): ViewModel()