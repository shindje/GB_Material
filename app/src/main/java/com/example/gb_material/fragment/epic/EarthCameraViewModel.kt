package com.example.gb_material.fragment.epic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb_material.BuildConfig
import com.example.gb_material.web.RetrofitImpl
import com.example.gb_material.web.epic.EPICServerResponseData
import com.example.gb_material.web.epic.EarthCameraData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EarthCameraViewModel (
        private val liveDataForViewToObserve: MutableLiveData<EarthCameraData> = MutableLiveData(),
        private val retrofitImpl: RetrofitImpl = RetrofitImpl()
) : ViewModel() {

    fun getData(): LiveData<EarthCameraData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = EarthCameraData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            EarthCameraData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getEPICRetrofitImpl().getEarthCameraNatural(apiKey).enqueue(object :
                    Callback<List<EPICServerResponseData>> {
                override fun onResponse(
                        call: Call<List<EPICServerResponseData>>,
                        response: Response<List<EPICServerResponseData>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                                EarthCameraData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                    EarthCameraData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                    EarthCameraData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<List<EPICServerResponseData>>, t: Throwable) {
                    liveDataForViewToObserve.value = EarthCameraData.Error(t)
                }
            })
        }
    }
}