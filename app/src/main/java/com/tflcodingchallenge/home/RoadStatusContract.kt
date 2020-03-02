package com.tflcodingchallenge.home

import com.tflcodingchallenge.Response
import com.tflcodingchallenge.RoadStatus
import com.tflcodingchallenge.model.ErrorResponse

interface RoadStatusContract {
    interface View {
        fun showResults(results:List<RoadStatus>)
        fun showError(error: String)
    }

    interface Presenter {
        fun getStatus(roadId : String)
    }
}