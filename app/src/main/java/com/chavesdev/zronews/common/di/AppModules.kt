package com.chavesdev.zronews.common.di

import com.chavesdev.zronews.auth.di.authModule
import com.chavesdev.zronews.login.di.loginModule
import com.chavesdev.zronews.main.di.mainModule
import com.chavesdev.zronews.news.di.newsModule
import com.chavesdev.zronews.register.di.registerModule

val appModules = listOf(baseNetworkModule, authModule, mainModule, loginModule, registerModule, newsModule)