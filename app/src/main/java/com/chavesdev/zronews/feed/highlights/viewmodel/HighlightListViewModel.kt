package com.chavesdev.zronews.feed.highlights.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chavesdev.zronews.auth.AuthManager
import com.chavesdev.zronews.common.util.ERRORCODE
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.feed.highlights.repo.HighlightsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HighlightListViewModel(
    private val authManager: AuthManager,
    private val highlightsRepo: HighlightsRepo,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    ViewModel() {
    val highligthsListState = MutableLiveData<LoadState>(LoadState.READY)

    fun loadHighlights() {
        highligthsListState.postValue(LoadState.LOADING)
        viewModelScope.launch(dispatcher) {
            val token = authManager.getBearerToken()
            token?.let {
                val response = highlightsRepo.loadHighlits(token)
                response?.let {
                    if (it.highlights != null && it.code == null) {
                        highligthsListState.postValue(LoadState.SUCCESS(response.highlights))
                    } else {
                        highligthsListState.postValue(
                            LoadState.ERROR(
                                ERRORCODE.fromValue(it.code)?.label,
                                it.message
                            )
                        )
                    }
                } ?: kotlin.run {
                    highligthsListState.postValue(LoadState.ERROR(ERRORCODE.OFFLINE.label))
                }
            }

        }
    }
}