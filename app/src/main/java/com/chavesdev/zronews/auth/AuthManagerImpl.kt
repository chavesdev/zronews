package com.chavesdev.zronews.auth

import android.content.Context
import com.chavesdev.zronews.common.util.PreferenceUtil

class AuthManagerImpl(private val context: Context) : AuthManager {

    override fun getToken(): String? = PreferenceUtil.getStringPref(context, KEY_TOKEN, null)

    override fun getBearerToken(): String? {
        return getToken()?.let { "Bearer $it" }
    }

    override fun storeToken(token: String) {
        PreferenceUtil.setStringPref(context, KEY_TOKEN, token)
    }

    companion object {
        const val KEY_TOKEN = "user_token"
    }
}