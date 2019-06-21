package com.pvasilev.aviasales

import android.app.Application
import com.pvasilev.aviasales.di.AppModule
import toothpick.Toothpick

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Toothpick.openScope("AppScope").apply {
            installModules(AppModule())
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        Toothpick.closeScope("AppScope")
    }
}