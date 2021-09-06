package com.chavesdev.zronews.register.data.remote.datasource

import com.chavesdev.zronews.register.data.remote.models.RegisterRequest
import com.chavesdev.zronews.register.data.remote.models.RegisterResponse
import com.chavesdev.zronews.register.data.remote.service.RegisterApi
import retrofit2.Response

class RegisterDataSourceImpl(private val registerApi: RegisterApi) : RegisterDataSource {
    override suspend fun register(registerRequest: RegisterRequest): Response<RegisterResponse> =
        registerApi.singnup(registerRequest)
}