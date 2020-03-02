package com.tflcodingchallenge.model


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("exceptionType")
    val exceptionType: String?,
    @SerializedName("httpStatus")
    val httpStatus: String?,
    @SerializedName("httpStatusCode")
    val httpStatusCode: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("relativeUri")
    val relativeUri: String?,
    @SerializedName("timestampUtc")
    val timestampUtc: String?
)