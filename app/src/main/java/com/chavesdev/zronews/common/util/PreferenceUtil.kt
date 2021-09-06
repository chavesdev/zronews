package com.chavesdev.zronews.common.util

import android.content.Context
import androidx.preference.PreferenceManager

object PreferenceUtil {

    fun getStringPref(context: Context, key: String, defaultValue: String?): String? {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue)
    }

    fun setStringPref(context: Context, key: String, value: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(key, value)
            .apply()
    }
}