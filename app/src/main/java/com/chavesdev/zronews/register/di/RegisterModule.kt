package com.chavesdev.zronews.register.di

import com.chavesdev.zronews.register.data.remote.datasource.RegisterDataSource
import com.chavesdev.zronews.register.data.remote.datasource.RegisterDataSourceImpl
import com.chavesdev.zronews.register.data.remote.service.RegisterApi
import com.chavesdev.zronews.register.repo.RegisterMapper
import com.chavesdev.zronews.register.repo.RegisterMapperImpl
import com.chavesdev.zronews.register.repo.RegisterRepo
import com.chavesdev.zronews.register.repo.RegisterRepoImpl
import com.chavesdev.zronews.register.viewmodel.RegisterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val registerModule = module {

    single<RegisterApi> { get<Retrofit>().create(RegisterApi::class.java) }

    single<RegisterDataSource> { RegisterDataSourceImpl(get()) }

    single<RegisterMapper>{ RegisterMapperImpl() }

    single<RegisterRepo> { RegisterRepoImpl(get(), get(), get()) }

    viewModel { RegisterViewModel(get(), get()) }
}