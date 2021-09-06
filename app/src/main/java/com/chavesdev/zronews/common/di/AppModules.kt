package com.chavesdev.zronews.common.di

import android.content.Context
import com.chavesdev.zronews.auth.di.authModule
import com.chavesdev.zronews.login.di.loginModule
import com.chavesdev.zronews.main.di.mainModule
import com.chavesdev.zronews.register.di.registerModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModules = listOf(networkModule, authModule, mainModule, loginModule, registerModule)