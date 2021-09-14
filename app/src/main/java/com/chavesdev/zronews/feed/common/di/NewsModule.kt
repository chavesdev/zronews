package com.chavesdev.zronews.feed.common.di

import com.chavesdev.zronews.feed.common.data.remote.datasource.HighlightsDataSource
import com.chavesdev.zronews.feed.common.data.remote.datasource.HighlightsDataSourceImpl
import com.chavesdev.zronews.feed.common.data.remote.service.NewsApi
import com.chavesdev.zronews.feed.highlights.repo.HighlightsMapper
import com.chavesdev.zronews.feed.highlights.repo.HighlightsMapperImpl
import com.chavesdev.zronews.feed.highlights.repo.HighlightsRepo
import com.chavesdev.zronews.feed.highlights.repo.HighlightsRepoImpl
import com.chavesdev.zronews.feed.highlights.ui.adapter.HighlightsAdapter
import com.chavesdev.zronews.feed.lastnews.ui.adapter.NewsAdapter
import com.chavesdev.zronews.feed.highlights.viewmodel.HighlightListViewModel
import com.chavesdev.zronews.feed.lastnews.repo.NewsMapper
import com.chavesdev.zronews.feed.lastnews.repo.NewsMapperImpl
import com.chavesdev.zronews.feed.lastnews.repo.NewsRepo
import com.chavesdev.zronews.feed.lastnews.repo.NewsRepoImpl
import com.chavesdev.zronews.feed.lastnews.viewmodel.NewsViewModel
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