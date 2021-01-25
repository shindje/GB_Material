package com.example.gb_material.web.epic

sealed class EarthCameraData {
    data class Success(val serverResponseData: List<EPICServerResponseData>) : EarthCameraData()
    data class Error(val error: Throwable) : EarthCameraData()
    data class Loading(val progress: Int?) : EarthCameraData()
}