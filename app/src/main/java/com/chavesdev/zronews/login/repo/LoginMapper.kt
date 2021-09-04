package com.chavesdev.zronews.login.repo

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.login.data.remote.models.LoginResponse
import com.chavesdev.zronews.login.repo.models.LoginResponseModel

class LoginMapper : NetworkMapper<LoginResponseModel, LoginResponse> {

    override fun toModel(networkModel: LoginResponse): LoginResponseModel {
        return LoginResponseModel(networkModel.token).also {
            it.code = networkModel.code
            it.message = networkModel.message
        }
    }

    override fun toNetwork(model: LoginResponseModel): LoginResponse {
        TODO("Not yet implemented")
    }
}