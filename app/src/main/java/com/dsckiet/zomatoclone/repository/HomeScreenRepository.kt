package com.dsckiet.zomatoclone.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dsckiet.zomatoclone.api.ZomatoNetworkClient
import com.dsckiet.zomatoclone.api.ZomatoNetworkClient.API_KEY
import com.dsckiet.zomatoclone.model.BestRatedRestaurant
import com.dsckiet.zomatoclone.model.LocationDetails
import com.dsckiet.zomatoclone.model.Restaurant
import com.dsckiet.zomatoclone.models.ModelLocation
import com.dsckiet.zomatoclone.models.restaurant
import kotlinx.android.synthetic.main.restaurant_cell.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenRepository(val application: Application) {
    val showProgress = MutableLiveData<Boolean>()
    val showRestaurant = MutableLiveData<ModelLocation>()
    val resName = MutableLiveData<List<restaurant>>()


    fun getRestaurantDetails(entity_id: Int, entity_type: String) {
        showProgress.value = true
        Log.i("Activity: ","called")
        val retrofitService = ZomatoNetworkClient.getClient()
        val callApi = retrofitService.getLocationDetails( entity_id, entity_type)
        callApi.enqueue(object : Callback<ModelLocation> {
            override fun onFailure(call: Call<ModelLocation>, t: Throwable) {
                showProgress.value = false
                Log.e("Api Failed: ",call.request().url.toString())
                Toast.makeText(application, "Error + ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ModelLocation>,
                response: Response<ModelLocation>
            ) {
                showProgress.value = false
                Log.e("Api Success: ",call.request().url.toString())
                resName.value = response.body()?.best_rated_restaurant

//                resName.observe(this, Observer{
//                  it.restaurant_name = response.body()?.bestRatedRestaurant?.get(0)!!.restaurant.name)
//                }

//                Log.i("response",resName)

            }

        })
    }
}