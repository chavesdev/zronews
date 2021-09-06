package com.chavesdev.zronews.auth

interface AuthManager {
    fun getToken() : String?

    fun storeToken(token: String)
}