package com.chavesdev.zronews.feed.lastnews.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chavesdev.zronews.databinding.NewsListItemBinding
import com.chavesdev.zronews.feed.common.ui.OnItemClickListener
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel
import com.chavesdev.zronews.feed.common.viewmodel.NewsItemListViewModel

class NewsAdapter : PagingDataAdapter<NewsItemModel, NewsAdapter.NewsViewHolder>(NewsComparator) {

    lateinit var onItemClickListener: OnItemClickListener

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position)?.let { newsItemModel ->
            holder.bind(
                NewsItemListViewModel(
                    newsItemModel.title,
                    newsItemModel.description,
                    newsItemModel.imageUrl ?: ""
                ).apply {
                    onClick = View.OnClickListener { onItemClickListener.onNewsItemClicked(newsItemModel) }
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            NewsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class NewsViewHolder(private val binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
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