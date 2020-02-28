package com.tflcodingchallenge.home

import com.tflcodingchallenge.Response
import com.tflcodingchallenge.RoadStatus

interface RoadStatusContract {
    interface View {
        fun showResults(results:Response)
        fun showError(message: String)
    }

    interface Presenter {
        fun getStatus(roadId : String)
    }
}