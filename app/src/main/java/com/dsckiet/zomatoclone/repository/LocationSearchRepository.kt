package com.dsckiet.zomatoclone.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dsckiet.zomatoclone.api.ZomatoNetworkClient
import com.dsckiet.zomatoclone.models.LocationSearch
import com.dsckiet.zomatoclone.models.LocationSuggestionX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationSearchRepository(val application: Application) {
    val locationSearch = MutableLiveData<List<LocationSuggestionX>>()
    val showProgress = MutableLiveData<Boolean>()
    fun searchLocation(query: String) {
        showProgress.value = true
        val retrofitService = ZomatoNetworkClient.getClient()
        val callApi = retrofitService.getCityByName(query)
        callApi.enqueue(object : Callback<LocationSearch> {
            override fun onFailure(call: Call<LocationSearch>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LocationSearch>,
                response: Response<LocationSearch>
            ) {
                showProgress.value = false
                locationSearch.value = response.body()?.locationSuggestions
            }

        })
    }
}