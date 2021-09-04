package com.chavesdev.zronews.login.di

import com.chavesdev.zronews.common.repo.NetworkMapper
import com.chavesdev.zronews.login.data.remote.datasource.LoginDataSource
import com.chavesdev.zronews.login.data.remote.datasource.LoginDataSourceImpl
import com.chavesdev.zronews.login.data.remote.models.LoginResponse
import com.chavesdev.zronews.login.data.remote.service.LoginApi
import com.chavesdev.zronews.login.repo.LoginMapper
import com.chavesdev.zronews.login.repo.LoginRepo
import com.chavesdev.zronews.login.repo.LoginRepoImpl
import com.chavesdev.zronews.login.repo.models.LoginResponseModel
import com.chavesdev.zronews.login.viewmodel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val loginModule = module {

    single<LoginApi> { get<Retrofit>().create(LoginApi::class.java) }

    single<LoginDataSource> { LoginDataSourceImpl(get()) }

    single<NetworkMapper<LoginResponseModel, LoginResponse>> { LoginMapper() }

    single<LoginRepo> { LoginRepoImpl(get(), get(), get()) }

    viewModel { LoginViewModel(get()) }

}