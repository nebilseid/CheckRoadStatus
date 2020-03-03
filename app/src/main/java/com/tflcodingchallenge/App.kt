package com.tflcodingchallenge

import android.app.Application
import com.tflcodingchallenge.di.AppComponent
import com.tflcodingchallenge.di.DaggerAppComponent


class App : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .build()

    }

    fun getComponent() = appComponent
}