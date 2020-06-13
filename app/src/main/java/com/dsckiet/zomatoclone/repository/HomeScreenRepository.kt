package com.dsckiet.zomatoclone.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dsckiet.zomatoclone.api.ZomatoNetworkClient
import com.dsckiet.zomatoclone.api.ZomatoNetworkClient.API_KEY
import com.dsckiet.zomatoclone.model.LocationDetails
import com.dsckiet.zomatoclone.model.Restaurant
import kotlinx.android.synthetic.main.restaurant_cell.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenRepository(val application: Application) {
    val showProgress = MutableLiveData<Boolean>()
    val showRestaurant = MutableLiveData<LocationDetails>()
    val resName = MutableLiveData<Restaurant>()


    fun getRestaurantDetails(entity_id: Int, entity_type: String) {
        showProgress.value = true
        Log.i("Activity: ","called")
        val retrofitService = ZomatoNetworkClient.getClient()
        val callApi = retrofitService.getLocationDetails( entity_id, entity_type)
        callApi.enqueue(object : Callback<LocationDetails> {
            override fun onFailure(call: Call<LocationDetails>, t: Throwable) {
                showProgress.value = false
                Log.e("Api Failed: ",call.request().url.toString())
                Toast.makeText(application, "Error + ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<LocationDetails>,
                response: Response<LocationDetails>
            ) {
                showProgress.value = false
                Log.e("Api Success: ",call.request().url.toString())
                resName.value = response.body()?.bestRatedRestaurant?.get(0)!!.restaurant
//                resName.observe(this, Observer{
//                    it.restaurant_name = response.body()?.bestRatedRestaurant?.get(0)!!.restaurant.name)
//                }

//                Log.i("response",resName)

            }

        })
    }
}