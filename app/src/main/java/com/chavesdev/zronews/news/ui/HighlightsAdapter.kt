package com.chavesdev.zronews.news.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chavesdev.zronews.databinding.NewsListItemBinding
import com.chavesdev.zronews.news.viewmodel.NewsItemListViewModel

class HighlightsAdapter : RecyclerView.Adapter<HighlightsAdapter.HighlightViewHolder>() {

    private var highlightsList: MutableList<NewsItemListViewModel> = ArrayList()

    class HighlightViewHolder(private val binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: NewsItemListViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        return HighlightViewHolder(NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        holder.bind(highlightsList[position])
    }

    override fun getItemCount(): Int = highlightsList.size


    fun updateHighlights(newList: MutableList<NewsItemListViewModel>) {
        highlightsList = newList
        notifyDataSetChanged()
    }
}