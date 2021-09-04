package com.chavesdev.zronews.login.repo.models

import com.chavesdev.zronews.common.repo.models.BaseResponseModel

data class LoginResponseModel(
    val token: String?
) : BaseResponseModel()