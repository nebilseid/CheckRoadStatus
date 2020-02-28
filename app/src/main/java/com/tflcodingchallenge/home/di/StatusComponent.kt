package com.tflcodingchallenge.home.di

import com.tflcodingchallenge.di.AppComponent
import com.tflcodingchallenge.home.RoadStatusActivity
import dagger.Component


@Component(dependencies = [AppComponent::class],modules =[StatusModule::class])
@HomeScope

interface StatusComponent {
    fun inject (mainActivity: RoadStatusActivity)
}