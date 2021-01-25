package com.example.gb_material.web.pod

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

public val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey: String, @Query(value = "date") date: String?): Call<PODServerResponseData>
}