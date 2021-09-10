package com.chavesdev.zronews.news.di

import com.chavesdev.zronews.news.data.remote.datasource.NewsDataSource
import com.chavesdev.zronews.news.data.remote.datasource.NewsDataSourceImpl
import com.chavesdev.zronews.news.data.remote.service.NewsApi
import com.chavesdev.zronews.news.repo.HighlightsMapper
import com.chavesdev.zronews.news.repo.HighlightsMapperImpl
import com.chavesdev.zronews.news.repo.HighlightsRepo
import com.chavesdev.zronews.news.repo.HighlightsRepoImpl
import com.chavesdev.zronews.news.ui.HighlightsAdapter
import com.chavesdev.zronews.news.viewmodel.HighlightListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val newsModule = module {

    single<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }

    single<NewsDataSource> { NewsDataSourceImpl(get()) }

    single<HighlightsMapper> { HighlightsMapperImpl() }

    single<HighlightsRepo> { HighlightsRepoImpl(get(), get(), get()) }

    factory<HighlightsAdapter> { HighlightsAdapter() }

    viewModel { HighlightListViewModel(get(), get()) }
}