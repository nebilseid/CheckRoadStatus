package com.tflcodingchallenge.home

import com.tflcodingchallenge.model.RoadStatus
import com.tflcodingchallenge.base.BasePresenter
import com.tflcodingchallenge.base.BaseView

interface RoadStatusContract {
    interface View : BaseView {
        fun showResults(results: List<RoadStatus>)
        fun showError(error: String)
    }

    interface Presenter : BasePresenter<View> {
        fun getStatus(roadId: String)
    }
}