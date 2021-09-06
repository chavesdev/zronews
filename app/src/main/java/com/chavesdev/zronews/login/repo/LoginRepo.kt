package com.chavesdev.zronews.login.repo

import com.chavesdev.zronews.login.repo.models.LoginResponseModel

interface LoginRepo {
    suspend fun login(email: String, password: String): LoginResponseModel?
}