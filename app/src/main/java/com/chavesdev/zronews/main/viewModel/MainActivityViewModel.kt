package com.chavesdev.zronews.main.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chavesdev.zronews.auth.AuthManager
import com.chavesdev.zronews.common.util.LoadState
import kotlinx.coroutines.launch

class MainActivityViewModel(private val authManager: AuthManager) : ViewModel() {

    val authState = MutableLiveData<LoadState>(LoadState.READY)

    fun checkAuth() {
        authState.postValue(LoadState.LOADING)
        viewModelScope.launch {
            val token = authManager.getToken()
            authState.postValue(LoadState.SUCCESS(token))
        }
    }
}