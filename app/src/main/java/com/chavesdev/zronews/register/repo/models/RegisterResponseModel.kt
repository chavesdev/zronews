package com.chavesdev.zronews.register.repo.models

import com.chavesdev.zronews.common.repo.models.BaseResponseModel

data class RegisterResponseModel(
    val token: String? = null,
    var errors: List<ErrorRegisterModel>? = null
) : BaseResponseModel()

data class ErrorRegisterModel(
    val code: String,
    val field: String,
    val message: String
)
