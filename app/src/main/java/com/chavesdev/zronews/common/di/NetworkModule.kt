package com.chavesdev.zronews.common.di

import com.chavesdev.zronews.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addNetworkInterceptor(Interceptor {
                val requestBuilder: Request.Builder = it.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")
                requestBuilder.addHeader("Accept", "*/*")
                it.proceed(requestBuilder.build())
            }).build()
    }

    single {
        GsonConverterFactory.create()
    }

    single {
        Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(get())
            .addConverterFactory(get())
            .build()
    }
}