package com.tflcodingchallenge.home.di

import com.tflcodingchallenge.home.RoadStatusContract
import com.tflcodingchallenge.home.RoadStatusPresenter
import com.tflcodingchallenge.network.RoadStatusService
import dagger.Module
import dagger.Provides


@Module
class StatusModule(private val view : RoadStatusContract.View){

    @HomeScope
    @Provides
    fun provideStatusPresenter (statusService: RoadStatusService):
            RoadStatusContract.Presenter{
        return RoadStatusPresenter(statusService,view)
    }

}