package com.chavesdev.zronews.login.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chavesdev.zronews.auth.AuthManager
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.login.repo.LoginRepo
import com.chavesdev.zronews.login.repo.models.LoginResponseModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel

    private val loginRepo: LoginRepo = mockk(relaxed = true)

    private val authManager: AuthManager = mockk(relaxed = true)

    @get:Rule
    val testCoroutineRule = InstantTaskExecutorRule()

    @Test
    fun checkViewModelStartsReady() {
        loginViewModel = LoginViewModel(loginRepo, authManager, Dispatchers.Unconfined)
        assertEquals(LoadState.READY, loginViewModel.loginState.value)
    }

    @Test
    fun checkErrorResponse() {
        //given
        coEvery { loginRepo.login(any(), any()) } returns invalidResponse

        //when
        loginViewModel = LoginViewModel(loginRepo, authManager, Dispatchers.Unconfined)
        fillUserAndTryLogin()

        //then
        assert(loginViewModel.loginState.value is LoadState.ERROR)
    }

    @Test
    fun checkSuccessResponse() {
        //given
        coEvery { loginRepo.login(any(), any()) } returns successResponse

        //when
        loginViewModel = LoginViewModel(loginRepo, authManager, Dispatchers.Unconfined)
        fillUserAndTryLogin()

        //then
        assert(loginViewModel.loginState.value is LoadState.SUCCESS)
    }

    private fun fillUserAndTryLogin() {
        loginViewModel.email.postValue("john@doe.com")
        loginViewModel.password.postValue("123456")
        loginViewModel.tryLogin()
    }

    companion object {
        val invalidResponse = LoginResponseModel(null).apply {
            code = "INVALID_CREDENTIALS"
            message = "Invalid credentials"
        }

        val successResponse = LoginResponseModel("2384kdjhfkheriweuyr")
    }
}