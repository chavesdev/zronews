package com.chavesdev.zronews.register.repo

import com.chavesdev.zronews.register.repo.models.RegisterResponseModel

interface RegisterRepo {
    suspend fun register(name: String, email: String, password: String) : RegisterResponseModel?
}