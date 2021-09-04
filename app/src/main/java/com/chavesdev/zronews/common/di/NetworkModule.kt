package com.chavesdev.zronews.common.di

import com.chavesdev.zronews.BuildConfig
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Gson>{ Gson() }

    single { provideHttpLogginIterceptor() }

    factory { provideOkHttpClient(get()) }

    single { provideGsonConverterFactory() }

    single { provideRetrofit(get(), get()) }
}

fun provideHttpLogginIterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(Interceptor {
            val requestBuilder: Request.Builder = it.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.addHeader("Accept", "*/*")
            it.proceed(requestBuilder.build())
        })
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(client)
        .addConverterFactory(gsonConverterFactory)
        .build()
}