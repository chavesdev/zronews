package com.chavesdev.zronews.register.data.remote.service

import com.chavesdev.zronews.register.data.remote.models.RegisterRequest
import com.chavesdev.zronews.register.data.remote.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("/v1/client/auth/signup")
    suspend fun singnup(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}