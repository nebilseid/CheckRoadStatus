package com.tflcodingchallenge.network

import com.tflcodingchallenge.Response
import com.tflcodingchallenge.RoadStatus
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RoadStatusService {
    @GET("/road/{id}/")
    fun getStatus(@Path("id") id : String): Observable<List<RoadStatus>>
}