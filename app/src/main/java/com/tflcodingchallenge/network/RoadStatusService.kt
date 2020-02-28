package com.tflcodingchallenge.network

import com.tflcodingchallenge.Response
import com.tflcodingchallenge.RoadStatus
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RoadStatusService {
    @GET("{roadId}")
    fun getStatus(@Path("roadId") roadId : String): Observable<Response>
}