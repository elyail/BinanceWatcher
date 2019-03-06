package com.gabdullinae.binancewatcher

import android.app.Application
import com.gabdullinae.binancewatcher.di.DI

class BinanceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DI.init(this)
    }
}