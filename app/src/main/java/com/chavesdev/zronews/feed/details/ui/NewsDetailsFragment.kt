package com.chavesdev.zronews.feed.details.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chavesdev.zronews.databinding.FragmentNewsDetailsBinding
import com.chavesdev.zronews.feed.common.repo.models.NewsItemModel
import com.chavesdev.zronews.feed.details.viewmodel.NewsDetailsViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class NewsDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailsBinding
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if(bundle == null){
            Log.e(TAG, "No arguments")
            return
        }


        val args = NewsDetailsFragmentArgs.fromBundle(bundle)
        showNews(args.paramNews)
        setUpBottomSheet()
    }

    private fun showNews(newsItemModel: NewsItemModel) {
        val viewModel = NewsDetailsViewModel.fromNewsItemModel(newsItemModel)
        binding.viewModel = viewModel
    }

    private fun setUpBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetDetails)
        bottomSheetBehavior.isHideable = false

        val childLayoutParams: ViewGroup.LayoutParams = binding.bottomSheetDetails.layoutParams
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        childLayoutParams.height = displayMetrics.heightPixels

        binding.bottomSheetDetails.layoutParams = childLayoutParams
    }

    companion object{
        const val TAG = "NewsDetailsFragment"
    }
}