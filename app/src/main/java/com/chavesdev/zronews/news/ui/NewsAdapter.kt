package com.chavesdev.zronews.news.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chavesdev.zronews.databinding.NewsListItemBinding
import com.chavesdev.zronews.news.data.remote.models.NewsItemResponse
import com.chavesdev.zronews.news.repo.models.NewsItemModel
import com.chavesdev.zronews.news.viewmodel.NewsItemListViewModel

class NewsAdapter: PagingDataAdapter<NewsItemModel, NewsAdapter.NewsViewHolder>(NewsComparator) {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(
                NewsItemListViewModel(
                    it.title,
                    it.description,
                    it.imageUrl ?: ""
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    class NewsViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: NewsItemListViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }


    object NewsComparator : DiffUtil.ItemCallback<NewsItemModel>() {
        override fun areItemsTheSame(
            oldItem: NewsItemModel,
            newItem: NewsItemModel
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: NewsItemModel,
            newItem: NewsItemModel
        ): Boolean {
            return oldItem.author == newItem.author &&
                    oldItem.content == newItem.content &&
                    oldItem.description == newItem.description &&
                    oldItem.imageUrl == newItem.imageUrl
        }

    }
}