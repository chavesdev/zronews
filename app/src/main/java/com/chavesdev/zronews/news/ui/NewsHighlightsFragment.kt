package com.chavesdev.zronews.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.chavesdev.zronews.R
import com.chavesdev.zronews.common.util.ERRORCODE
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.databinding.FragmentNewsHighlightsBinding
import com.chavesdev.zronews.news.viewmodel.HighlightListViewModel
import com.chavesdev.zronews.news.viewmodel.NewsItemListViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class NewsHighlightsFragment : Fragment() {

    private val highlightListViewModel: HighlightListViewModel by viewModel()
    private val highlightsAdapter: HighlightsAdapter by inject()

    private lateinit var binding: FragmentNewsHighlightsBinding
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsHighlightsBinding.inflate(layoutInflater, container, false)
        binding.recyclerHighlights.adapter = highlightsAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onResume() {
        super.onResume()
        registerObservables()
        highlightListViewModel.loadHighlights()
    }

    private fun registerObservables() {
        highlightListViewModel.highligthsListState.observe(this, {
            when(it){
                LoadState.LOADING -> {
                    binding.progressHighlights.isVisible = true
                }

                is LoadState.SUCCESS -> {
                    binding.progressHighlights.isVisible = false
                    highlightsAdapter.updateHighlights(it.data as MutableList<NewsItemListViewModel>)
                }

                LoadState.READY -> {
                    binding.progressHighlights.isVisible = false
                }
                is LoadState.ERROR -> {
                    val errorCode = ERRORCODE.fromValue(it.code)
                    if(errorCode != null){
                        when(errorCode){
                            ERRORCODE.OFFLINE -> goToLogin()
                        }
                    }
                   MaterialAlertDialogBuilder(requireContext()).apply {
                       setTitle("Something wrong")
                   }.show()
                }
            }
        })
    }

    private fun goToLogin() {
        navController?.navigate(R.id.action_newsHighlightsFragment_to_mainFragment)
    }
}