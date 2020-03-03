package com.tflcodingchallenge.home

import com.google.gson.Gson
import com.tflcodingchallenge.base.Presenter
import com.tflcodingchallenge.model.ErrorResponse
import com.tflcodingchallenge.network.RoadStatusService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RoadStatusPresenter(
    private val statusService: RoadStatusService
) : RoadStatusContract.Presenter, Presenter<RoadStatusContract.View>() {

    private val compositeDisposable = CompositeDisposable()

    override fun getStatus(roadId: String) {
        compositeDisposable.add(
            statusService.getStatus(id = roadId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {

                        if (it.body().isNullOrEmpty()) {
                            view?.showError("No List found")
                        } else {
                            view?.showResults(it.body()!!)
                        }
                    } else {
                        val error = Gson().fromJson(
                            it.errorBody()?.string(),
                            ErrorResponse::class.java
                        )
                        if (error?.message == null) {
                            view?.showError("An unknown error happened: Unable to parse error body")
                        } else {
                            view?.showError(error.message)
                        }
                    }
                },
                    { failure -> view?.showError(failure?.message ?: "An unknown error happened") })
        )
    }

}