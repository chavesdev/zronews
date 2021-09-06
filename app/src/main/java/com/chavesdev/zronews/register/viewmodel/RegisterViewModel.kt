package com.chavesdev.zronews.register.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chavesdev.zronews.auth.AuthManager
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.register.repo.RegisterRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerRepo: RegisterRepo,
    private val authManager: AuthManager,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val registerState: MutableLiveData<LoadState> = MutableLiveData(LoadState.READY)
    val name = MutableLiveData(String())
    val email = MutableLiveData(String())
    val password = MutableLiveData(String())
    val passwordConfirm = MutableLiveData(String())
    val formValid = MutableLiveData(false)

    val nameError = MutableLiveData(String())
    val emailError = MutableLiveData(String())
    val passwordError = MutableLiveData(String())
    val passwordConfirmError = MutableLiveData(String())

    fun tryRegister() {
        registerState.postValue(LoadState.LOADING)
        viewModelScope.launch(dispatcher) {
            val response = registerRepo.register(name.value!!, email.value!!, password.value!!)
            if (response?.token != null) {
                authManager.storeToken(response.token)
                registerState.postValue(LoadState.SUCCESS(response.token))
            } else {
                registerState.postValue(LoadState.ERROR(errors = response?.errors))
            }
        }
    }

    fun checkForm() {
        formValid.postValue(
            (name.value?.isNotEmpty() ?: false
                    && password.value?.isNotEmpty() ?: false
                    && email.value?.isNotEmpty() ?: false
                    && passwordConfirmed())
        )
    }

    private fun passwordConfirmed(): Boolean {
        val isConfirmed = passwordConfirm.value == password.value && password.value != null
        if(isConfirmed.not()) {
            passwordConfirmError.postValue("The passwords does not matches")
        } else {
            passwordConfirmError.postValue("")
        }
        return isConfirmed
    }
}