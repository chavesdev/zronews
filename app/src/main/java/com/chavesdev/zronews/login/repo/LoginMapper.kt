package com.chavesdev.zronews.login.repo

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.login.data.remote.models.LoginResponse
import com.chavesdev.zronews.login.repo.models.LoginResponseModel

interface LoginMapper: NetworkMapper<LoginResponseModel, LoginResponse> {
}