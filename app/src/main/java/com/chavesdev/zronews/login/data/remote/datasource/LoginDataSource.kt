package com.chavesdev.zronews.login.data.remote.datasource

import com.chavesdev.zronews.login.data.remote.models.LoginRequest
import com.chavesdev.zronews.login.data.remote.models.LoginResponse
import retrofit2.Response

interface LoginDataSource {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse>
}