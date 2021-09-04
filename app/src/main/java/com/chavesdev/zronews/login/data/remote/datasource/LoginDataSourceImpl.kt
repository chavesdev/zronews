package com.chavesdev.zronews.login.data.remote.datasource

import com.chavesdev.zronews.login.data.remote.models.LoginRequest
import com.chavesdev.zronews.login.data.remote.models.LoginResponse
import com.chavesdev.zronews.login.data.remote.service.LoginApi
import retrofit2.Response

class LoginDataSourceImpl(private val loginApi: LoginApi) : LoginDataSource {

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> =
        loginApi.login(loginRequest)
}