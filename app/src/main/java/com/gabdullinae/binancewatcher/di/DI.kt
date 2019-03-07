package com.gabdullinae.binancewatcher.di

import android.content.Context
import com.gabdullinae.binancewatcher.di.components.AppComponent
import com.gabdullinae.binancewatcher.di.components.DaggerAppComponent

object DI {
    lateinit var app: AppComponent

    fun init(context: Context) {
        this.app = DaggerAppComponent
            .builder()
            .application(context)
            .build()
    }
}