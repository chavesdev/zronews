package com.chavesdev.zronews.feed.common.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel

class NewsItemListViewModel(
    val title: String,
    val description: String,
    val imageUrl: String
): ViewModel() {
    lateinit var onClick: View.OnClickListener
}