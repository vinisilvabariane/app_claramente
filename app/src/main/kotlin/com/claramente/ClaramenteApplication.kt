package com.claramente

import android.app.Application
import com.claramente.di.AppContainer

class ClaramenteApplication : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
