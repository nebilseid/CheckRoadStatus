package com.tflcodingchallenge.model

import com.google.gson.annotations.SerializedName

data class RoadStatus(

    @SerializedName("\$type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("statusSeverity") val statusSeverity: String,
    @SerializedName("statusSeverityDescription") val statusSeverityDescription: String,
    @SerializedName("bounds") val bounds: String,
    @SerializedName("envelope") val envelope: String,
    @SerializedName("url") val url: String

)