package com.chavesdev.zronews.common.util

import com.google.gson.annotations.SerializedName
import java.io.Serializable

sealed class LoadState {
    object READY : LoadState()
    object LOADING : LoadState()
    class SUCCESS(val data: Any?) : LoadState()
    class ERROR(val code: String? = null, val msg: String? = null, val errors: List<Any>? = null) :
        LoadState()
}

enum class ERRORCODE(val label: String) : Serializable {
    @SerializedName("offline")
    OFFLINE("offline"),

    @SerializedName("401")
    UNAUTHORIZED("401");

    companion object {
        fun fromValue(label: String?): ERRORCODE {
            return label?.let { values().first { errorcode -> errorcode.label == label } }
                ?: kotlin.run { OFFLINE }
        }
    }
}