package com.chavesdev.zronews.register.repo

import com.chavesdev.zronews.register.data.remote.datasource.RegisterDataSource
import com.chavesdev.zronews.register.data.remote.models.RegisterRequest
import com.chavesdev.zronews.register.data.remote.models.RegisterResponse
import com.chavesdev.zronews.register.repo.models.RegisterResponseModel
import com.google.gson.Gson

class RegisterRepoImpl(
    private val registerDataSource: RegisterDataSource,
    private val networkMapper: RegisterMapper,
    private val gson: Gson
): RegisterRepo {

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): RegisterResponseModel? {
        val response = registerDataSource.register(RegisterRequest(name,email, password))
        if(response.isSuccessful) {
            return networkMapper.toModel(response.body()!!)
        } else {
            when(response.code()) {
                422 -> {
                    val errorBody = response.errorBody()
                    val json: RegisterResponse = gson.fromJson(errorBody?.string(), RegisterResponse::class.java)
                    return networkMapper.toModel(json)
                }
            }

        }
        return null
    }
}