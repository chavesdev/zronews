package com.chavesdev.zronews.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.login.repo.LoginRepo
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepo: LoginRepo) : ViewModel() {

    val loginState = MutableLiveData<LoadState>(LoadState.READY)
    val username = MutableLiveData(String())
    val password = MutableLiveData(String())
    val formValid = MutableLiveData(false)

    fun tryLogin() {
        loginState.postValue(LoadState.LOADING)
        viewModelScope.launch {
            val response = loginRepo.login(username.value!!, password.value!!)
            if(response?.token != null) {
                loginState.postValue(LoadState.SUCCESS(response.token))
            } else {
                loginState.postValue(LoadState.ERROR(response?.code!!, response.message!!))
            }
        }
    }

    fun checkForm() {
        formValid.postValue(
            (username.value?.isNotEmpty() ?: false && password.value?.isNotEmpty() ?: false)
        )
    }
}