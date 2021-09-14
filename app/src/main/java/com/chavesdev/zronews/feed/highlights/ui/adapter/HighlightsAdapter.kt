package com.chavesdev.zronews.feed.highlights.ui.adapter

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chavesdev.zronews.databinding.NewsListItemBinding
import com.chavesdev.zronews.main.ui.MainActivity
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel
import com.chavesdev.zronews.feed.common.ui.OnItemClickListener
import com.chavesdev.zronews.feed.common.viewmodel.NewsItemListViewModel

class HighlightsAdapter : RecyclerView.Adapter<HighlightsAdapter.HighlightViewHolder>() {

    private var highlightsList: MutableList<NewsItemModel> = ArrayList()
    private var screenWidth = 0
    lateinit var onItemClickListener: OnItemClickListener

    class HighlightViewHolder(var binding: NewsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: NewsItemListViewModel) {
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighlightViewHolder {
        val displayMetrics = DisplayMetrics()
        (parent.context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels
        return HighlightViewHolder(
            NewsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HighlightViewHolder, position: Int) {
        adjustWidth(holder)
        val newsItemModel = highlightsList[position]
        holder.bind(NewsItemListViewModel(
            newsItemModel.title,
            newsItemModel.description,
            newsItemModel.imageUrl ?: ""
        ).apply {
            onClick = View.OnClickListener {
                onItemClickListener.onNewsItemClicked(newsItemModel)
            }
        }
        )
    }

    override fun getItemCount(): Int = highlightsList.size

    fun updateHighlights(newList: MutableList<NewsItemModel>) {
        highlightsList = newList
        notifyDataSetChanged()
    }

    private fun adjustWidth(holder: HighlightViewHolder) {
        val itemWidth = screenWidth * HIGHLIGHT_PERCENT_WIDTH
        val lp = holder.binding.root.layoutParams
        lp.height = lp.height
        lp.width = itemWidth.toInt()
        holder.binding.root.layoutParams = lp
        holder.binding.root.setPadding(0, 0, HIGHLIGHT_PADDING, HIGHLIGHT_PADDING)
    }

    companion object {
        const val HIGHLIGHT_PERCENT_WIDTH = 0.6
        const val HIGHLIGHT_PADDING = 30
    }
}