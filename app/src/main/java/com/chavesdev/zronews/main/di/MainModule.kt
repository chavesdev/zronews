package com.chavesdev.zronews.main.di

import com.chavesdev.zronews.main.viewModel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainActivityViewModel(get()) }
}