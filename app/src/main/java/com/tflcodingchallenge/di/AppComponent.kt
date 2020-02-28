package com.tflcodingchallenge.di

import com.tflcodingchallenge.network.RoadStatusService
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])

@Singleton
interface AppComponent {
    fun roadStatusService(): RoadStatusService

}