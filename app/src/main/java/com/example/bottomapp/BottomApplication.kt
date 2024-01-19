package com.example.bottomapp

import android.app.Application
import com.example.bottomapp.data.AppContainer
import com.example.bottomapp.data.DefaultAppContainer

class BottomApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}