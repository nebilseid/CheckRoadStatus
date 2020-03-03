package com.tflcodingchallenge.network

import com.tflcodingchallenge.model.RoadStatus
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RoadStatusService {
    @GET("/road/{id}/")
    fun getStatus(@Path("id") id : String): Observable<Response<List<RoadStatus>>>

}