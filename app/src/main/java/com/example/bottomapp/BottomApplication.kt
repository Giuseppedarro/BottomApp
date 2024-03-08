package com.example.bottomapp

import android.app.Application
import com.example.bottomapp.data.AppContainer
import com.example.bottomapp.data.DefaultAppContainer
import com.example.bottomapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class BottomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BottomApplication)
            modules(appModule)
        }
    }
}