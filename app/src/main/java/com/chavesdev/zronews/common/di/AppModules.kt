package com.chavesdev.zronews.common.di

import com.chavesdev.zronews.login.di.loginModule
import com.chavesdev.zronews.register.di.registerModule

val appModules = listOf(networkModule, loginModule, registerModule)