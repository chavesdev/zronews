package com.chavesdev.zronews.feed.common.repo.models

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import java.util.*

@Keep
@Parcelize
data class NewsItemModel(
    val title: String,
    val description: String,
    val content: String,
    val author: String,
    val publishedAt: Date?,
    val url: String,
    val imageUrl: String?
) : Parcelable