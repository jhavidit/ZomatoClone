package com.dsckiet.zomatoclone.api


import com.dsckiet.zomatoclone.models.LocationSearch
import com.dsckiet.zomatoclone.models.ModelLocation
import com.dsckiet.zomatoclone.models.ModelLocationData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ZomatoNetworkInterface {
    @GET("cities")
    fun getCityByName(
        @Query("q") cityName: String
    ): Call<LocationSearch>

    @GET("cities")
    fun getCityByCord(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Call<ModelLocationData>

    @GET("location_details")
    fun getLocationDetails(
        @Query("entity_id") entityId: Int,
        @Query("entity_type") entityType: String
    ): Call<ModelLocation>

}
