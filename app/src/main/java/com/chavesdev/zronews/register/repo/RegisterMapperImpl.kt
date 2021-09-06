package com.chavesdev.zronews.register.repo

import com.chavesdev.zronews.register.data.remote.models.ErrorRegisterResponse
import com.chavesdev.zronews.register.data.remote.models.RegisterResponse
import com.chavesdev.zronews.register.repo.models.ErrorRegisterModel
import com.chavesdev.zronews.register.repo.models.RegisterResponseModel

class RegisterMapperImpl: RegisterMapper {

    override fun toModel(networkModel: RegisterResponse): RegisterResponseModel {
        return RegisterResponseModel(networkModel.token, transform(networkModel.errors)).also {
            it.code = networkModel.code
            it.message = networkModel.message
        }
    }

    private fun transform(errors: List<ErrorRegisterResponse>?): List<ErrorRegisterModel>? {
        return errors?.map {
            ErrorRegisterModel(it.code, it.field, it.message)
        }
    }

    override fun toNetwork(model: RegisterResponseModel): RegisterResponse {
        TODO("Not yet implemented")
    }
}