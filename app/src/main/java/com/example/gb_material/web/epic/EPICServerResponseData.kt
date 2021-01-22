package com.example.gb_material.web.epic

import com.google.gson.annotations.SerializedName

data class EPICServerResponseData (
    @field:SerializedName("image") val image: String?,
    @field:SerializedName("date") val date: String?,
)