package com.chavesdev.zronews.login.data.remote.service

import com.chavesdev.zronews.login.data.remote.models.LoginRequest
import com.chavesdev.zronews.login.data.remote.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("/v1/client/auth/signin")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}