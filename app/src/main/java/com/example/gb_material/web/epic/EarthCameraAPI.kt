package com.example.gb_material.web.epic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EarthCameraAPI {
    @GET("EPIC/api/natural")
    fun getEarthCameraNatural(@Query("api_key") apiKey: String): Call<List<EPICServerResponseData>>
}