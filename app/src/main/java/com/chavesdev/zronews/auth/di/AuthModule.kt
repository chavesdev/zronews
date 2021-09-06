package com.chavesdev.zronews.auth.di

import com.chavesdev.zronews.auth.AuthManager
import com.chavesdev.zronews.auth.AuthManagerImpl
import org.koin.dsl.module

val authModule = module {
    single<AuthManager> { AuthManagerImpl(get()) }
}