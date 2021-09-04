package com.chavesdev.zronews.login.repo

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.login.data.remote.datasource.LoginDataSource
import com.chavesdev.zronews.login.data.remote.models.LoginRequest
import com.chavesdev.zronews.login.data.remote.models.LoginResponse
import com.chavesdev.zronews.login.repo.models.LoginResponseModel
import com.google.gson.Gson

class LoginRepoImpl(
    private val loginDataSource: LoginDataSource,
    private val networkMapper: NetworkMapper<LoginResponseModel, LoginResponse>,
    private val gson: Gson
) : LoginRepo {

    override suspend fun login(username: String, password: String): LoginResponseModel? {
        val response = loginDataSource.login(LoginRequest(username, password))
        if(response.isSuccessful) {
            return networkMapper.toModel(response.body()!!)
        } else {
            when(response.code()) {
                401 -> {
                    val errorBody = response.errorBody()
                    val json: LoginResponse = gson.fromJson(errorBody?.string(), LoginResponse::class.java)
                    return networkMapper.toModel(json)
                }
            }

        }
        return null
    }

}