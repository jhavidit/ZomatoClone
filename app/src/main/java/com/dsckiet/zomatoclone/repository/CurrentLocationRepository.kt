package com.dsckiet.zomatoclone.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dsckiet.zomatoclone.api.ZomatoNetworkClient
import com.dsckiet.zomatoclone.model.LocationSearchItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrentLocationRepository(val application: Application) {
    val currentLocation = MutableLiveData<LocationSearchItem>()
    fun getCurrentLocation(lat: Double, lon: Double) {
        val retrofitService = ZomatoNetworkClient.getClient()
        val callApi = retrofitService.getCityByCord(lat, lon)
        callApi.enqueue(object :Callback<LocationSearchItem>{
            override fun onFailure(call: Call<LocationSearchItem>, t: Throwable) {
                Toast.makeText(application,"Couldn't Detect Your Location because ${t.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LocationSearchItem>,
                response: Response<LocationSearchItem>
            ) {
                currentLocation.value=response.body()
            }

        })
    }

}