package com.chavesdev.zronews.register.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chavesdev.zronews.common.util.LoadState
import com.chavesdev.zronews.register.repo.RegisterRepo
import com.chavesdev.zronews.register.repo.models.ErrorRegisterModel
import com.chavesdev.zronews.register.repo.models.RegisterResponseModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class RegisterViewModelTest {

    private lateinit var registerViewModel: RegisterViewModel

    private val registerRepo: RegisterRepo = mockk()

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @Test
    fun checkViewModelStartsReady() {
        registerViewModel = RegisterViewModel(registerRepo, Dispatchers.Unconfined)
        assertEquals(LoadState.READY, registerViewModel.registerState.value)
    }

    @Test
    fun checkErrorResponse() {
        //given
        coEvery { registerRepo.register(any(), any(), any()) } returns invalidResponse
        registerViewModel = RegisterViewModel(registerRepo, Dispatchers.Unconfined)

        //when
        fillUserIncorrectlyAndTryRegister()

        //then
        assert(registerViewModel.registerState.value is LoadState.ERROR)
    }

    @Test
    fun checkSuccessResponse() {
        //given
        coEvery { registerRepo.register(any(), any(), any()) } returns successResponse
        registerViewModel = RegisterViewModel(registerRepo, Dispatchers.Unconfined)

        //when
        fillUserCorrectly()
        registerViewModel.tryRegister()

        //then
        assert(registerViewModel.registerState.value is LoadState.SUCCESS)
    }

    @Test
    fun checkCorrectValuesMakeformValid() {
        //given
        registerViewModel = RegisterViewModel(registerRepo, Dispatchers.Main)

        //when
        fillUserCorrectly()
        registerViewModel.checkForm()

        //then
        assert(registerViewModel.formValid.value == true)
        assert(registerViewModel.nameError.value == String())
        assert(registerViewModel.emailError.value == String())
        assert(registerViewModel.passwordError.value == String())
        assert(registerViewModel.passwordConfirmError.value == String())

    }

    private fun fillUserIncorrectlyAndTryRegister() {
        registerViewModel.email.postValue("john@doe.com")
        registerViewModel.password.postValue("123456")
        registerViewModel.tryRegister()
    }

    private fun fillUserCorrectly() {
        registerViewModel.name.value = "John Doe"
        registerViewModel.email.value = "john@doe.com"
        registerViewModel.password.postValue("123456")
        registerViewModel.passwordConfirm.postValue("123456")
    }

    companion object {
        private val errors = listOf(ErrorRegisterModel("BLANK", "name", "Name can't be blank"))

        val invalidResponse = RegisterResponseModel(null, errors)

        val successResponse = RegisterResponseModel("2384kdjhfkheriweuyr")
    }
}