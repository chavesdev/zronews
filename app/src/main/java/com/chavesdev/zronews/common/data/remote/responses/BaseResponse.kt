package com.chavesdev.zronews.common.data.remote.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

abstract class BaseResponse : Serializable {

    @SerializedName("code")
    var code: String? = null

    @SerializedName("message")
    var message: String? = null
}
