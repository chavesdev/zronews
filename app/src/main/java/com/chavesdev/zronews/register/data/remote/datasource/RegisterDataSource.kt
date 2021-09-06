package com.chavesdev.zronews.register.data.remote.datasource

import com.chavesdev.zronews.register.data.remote.models.RegisterRequest
import com.chavesdev.zronews.register.data.remote.models.RegisterResponse
import retrofit2.Response

interface RegisterDataSource {
    suspend fun register(registerRequest: RegisterRequest) : Response<RegisterResponse>
}