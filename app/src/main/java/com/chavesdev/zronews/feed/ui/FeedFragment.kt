package com.chavesdev.zronews.feed.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.chavesdev.zronews.R
import com.chavesdev.zronews.common.util.ERRORCODE
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.databinding.FragmentNewsHighlightsBinding
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel
import com.chavesdev.zronews.feed.highlights.ui.adapter.HighlightsAdapter
import com.chavesdev.zronews.feed.lastnews.ui.adapter.NewsAdapter
import com.chavesdev.zronews.feed.common.ui.OnItemClickListener
import com.chavesdev.zronews.feed.highlights.viewmodel.HighlightListViewModel
import com.chavesdev.zronews.feed.lastnews.viewmodel.NewsViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FeedFragment : Fragment(), OnItemClickListener {

    private val highlightListViewModel: HighlightListViewModel by viewModel()
    private val newsViewModel: NewsViewModel by viewModel()
    private val highlightsAdapter: HighlightsAdapter by inject()
    private val newsAdapter: NewsAdapter by inject()

    private lateinit var binding: FragmentNewsHighlightsBinding
    private var navController: NavController? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsHighlightsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        newsAdapter.onItemClickListener = this
        highlightsAdapter.onItemClickListener = this
        binding.recyclerHighlights.adapter = highlightsAdapter
        binding.recyclerNews.adapter = newsAdapter
        highlightListViewModel.loadHighlights()
        setUpBottomSheet()
    }

    override fun onResume() {
        super.onResume()
        registerObservables()
    }

    private fun registerObservables() {
        highlightListViewModel.highligthsListState.observe(this, {
            when (it) {
                LoadState.LOADING -> {
                    binding.progressHighlights.isVisible = true
                }

                is LoadState.SUCCESS -> {
                    binding.progressHighlights.isVisible = false
                    highlightsAdapter.updateHighlights(it.data as MutableList<NewsItemModel>)
                }

                LoadState.READY -> {
                    binding.progressHighlights.isVisible = false
                }
                is LoadState.ERROR -> {
                    val errorCode = ERRORCODE.fromValue(it.code)
                    if (errorCode != null) {
                        when (errorCode) {
                            ERRORCODE.OFFLINE -> goToLogin()
                        }
                    }
                    MaterialAlertDialogBuilder(requireContext()).apply {
                        setTitle("Something wrong")
                    }.show()
                }
            }
        })

        with(newsViewModel) {
            lifecycleScope.launchWhenCreated {
                newsViewModel.newsList.collectLatest { pagingData ->
                    newsAdapter.submitData(pagingData)
                }
            }
        }

    }

    private fun goToLogin() {
        navController?.navigate(R.id.action_newsHighlightsFragment_to_mainFragment)
    }

    private fun setUpBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetNews)
        bottomSheetBehavior.isHideable = false

        val childLayoutParams: ViewGroup.LayoutParams = binding.bottomSheetNews.layoutParams
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        childLayoutParams.height = displayMetrics.heightPixels

        binding.bottomSheetNews.layoutParams = childLayoutParams
    }

    override fun onNewsItemClicked(newsItemModel: NewsItemModel) {
        val directions = FeedFragmentDirections.actionNewsHighlightsFragmentToNewsDetailsFragment(newsItemModel)
        navController?.navigate(directions)
    }
}