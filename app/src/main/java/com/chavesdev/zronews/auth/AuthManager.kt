package com.chavesdev.zronews.auth

interface AuthManager {
    fun getToken(): String?

    fun getBearerToken(): String?

    fun storeToken(token: String)
}