package com.tflcodingchallenge.home

import com.tflcodingchallenge.network.RoadStatusService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RoadStatusPresenter(
    private val statusService: RoadStatusService,private val view: RoadStatusContract.View
) : RoadStatusContract.Presenter {


    private val compositeDisposable = CompositeDisposable()

    override fun getStatus(roadId: String) {
        compositeDisposable.add(
            statusService.getStatus(id = roadId )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.showResults(it) },
                    { failure -> view.showError(failure?.message ?: "An unknown error happened") })
        )
    }
}