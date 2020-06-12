package com.example.soundboardapp

import android.app.Application
import android.content.Context

class SoundboardApp: Application() {
    companion object {
        lateinit var ApplicationContext: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }
}