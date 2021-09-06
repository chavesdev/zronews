package com.chavesdev.zronews.login.di

import com.chavesdev.zronews.login.data.remote.datasource.LoginDataSource
import com.chavesdev.zronews.login.data.remote.datasource.LoginDataSourceImpl
import com.chavesdev.zronews.login.data.remote.service.LoginApi
import com.chavesdev.zronews.login.repo.LoginMapper
import com.chavesdev.zronews.login.repo.LoginMapperImpl
import com.chavesdev.zronews.login.repo.LoginRepo
import com.chavesdev.zronews.login.repo.LoginRepoImpl
import com.chavesdev.zronews.login.viewmodel.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val loginModule = module {

    single<LoginApi> { get<Retrofit>().create(LoginApi::class.java) }

    single<LoginDataSource> { LoginDataSourceImpl(get()) }

    single<LoginMapper> { LoginMapperImpl() }

    single<LoginRepo> { LoginRepoImpl(get(), get(), get()) }

    viewModel { LoginViewModel(get(), get()) }

}