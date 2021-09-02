package com.chavesdev.zronews

import android.app.Application
import com.chavesdev.zronews.common.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class ZeroNewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ZeroNewsApplication)

            loadKoinModules(appModules)
        }
    }
}