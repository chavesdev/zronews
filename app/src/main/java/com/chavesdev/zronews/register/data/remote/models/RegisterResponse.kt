package com.chavesdev.zronews.register.data.remote.models

import com.chavesdev.zronews.common.data.remote.responses.BaseResponse
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("errors")
    val errors: List<ErrorRegisterResponse>? = null
) : BaseResponse()

data class ErrorRegisterResponse(
    @SerializedName("code")
    val code: String,
    @SerializedName("field")
    val field: String,
    @SerializedName("message")
    val message: String
)