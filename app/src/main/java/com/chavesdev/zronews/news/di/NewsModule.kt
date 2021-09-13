package com.chavesdev.zronews.news.di

import com.chavesdev.zronews.news.data.remote.datasource.HighlightsDataSource
import com.chavesdev.zronews.news.data.remote.datasource.HighlightsDataSourceImpl
import com.chavesdev.zronews.news.data.remote.datasource.NewsDataSource
import com.chavesdev.zronews.news.data.remote.service.NewsApi
import com.chavesdev.zronews.news.repo.*
import com.chavesdev.zronews.news.ui.HighlightsAdapter
import com.chavesdev.zronews.news.ui.NewsAdapter
import com.chavesdev.zronews.news.viewmodel.HighlightListViewModel
import com.chavesdev.zronews.news.viewmodel.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val newsModule = module {

    single<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }

    single<HighlightsDataSource> { HighlightsDataSourceImpl(get()) }

    single<HighlightsMapper> { HighlightsMapperImpl() }

    single<HighlightsRepo> { HighlightsRepoImpl(get(), get(), get()) }

    factory<HighlightsAdapter> { HighlightsAdapter() }

    viewModel { HighlightListViewModel(get(), get()) }

    //News
    factory<NewsAdapter> { NewsAdapter() }

    single<NewsMapper> { NewsMapperImpl() }

    single<NewsRepo> { NewsRepoImpl(get(), get(), get()) }

    viewModel { NewsViewModel(get()) }
}