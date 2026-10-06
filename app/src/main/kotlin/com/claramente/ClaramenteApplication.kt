package com.claramente

import com.claramente.di.AppContainer
import android.app.Application

class ClaramenteApplication : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
