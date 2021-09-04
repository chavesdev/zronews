package com.chavesdev.zronews.login.data.remote.models

import com.chavesdev.zronews.common.data.remote.responses.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String
) : BaseResponse()