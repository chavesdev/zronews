package com.chavesdev.zronews.news.ui

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.chavesdev.zronews.databinding.NewsListItemBinding
import com.chavesdev.zronews.main.ui.MainActivity
import com.chavesdev.zronews.news.viewmodel.NewsItemListViewModel

class HighlightsAdapter : RecyclerView.Adapter<HighlightsAdapter.HighlightViewHolder>() {

    private var highlightsList: MutableList<NewsItemListViewModel> = ArrayList()

    private var screenWidth = 0

    class HighlightViewHolder(var binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: NewsItemListViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val displayMetrics = DisplayMetrics()
        (parent.context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels
        return HighlightViewHolder(NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        val itemWidth = screenWidth * 0.6
        val lp = holder.binding.root.layoutParams
        lp.height = lp.height
        lp.width = itemWidth.toInt()
        holder.binding.root.layoutParams = lp
        holder.binding.root.setPadding(0,0,30,0)

        holder.bind(highlightsList[position])
    }

    override fun getItemCount(): Int = highlightsList.size


    fun updateHighlights(newList: MutableList<NewsItemListViewModel>) {
        highlightsList = newList
        notifyDataSetChanged()
    }
}