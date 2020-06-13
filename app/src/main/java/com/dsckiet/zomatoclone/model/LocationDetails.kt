package com.dsckiet.zomatoclone.model


import com.squareup.moshi.Json
import java.io.Serializable

data class LocationDetails(
    @Json(name = "best_rated_restaurant")
    val bestRatedRestaurant: List<BestRatedRestaurant>,
    val city: String,
    val location: LocationX,
    @Json(name = "nearby_res")
    val nearbyRes: List<String>,
    @Json(name = "nightlife_index")
    val nightlifeIndex: String,
    @Json(name = "nightlife_res")
    val nightlifeRes: String,
    @Json(name = "num_restaurant")
    val numRestaurant: Int,
    val popularity: String,
    @Json(name = "popularity_res")
    val popularityRes: String,
    val subzone: String,
    @Json(name = "subzone_id")
    val subzoneId: Int,
    @Json(name = "top_cuisines")
    val topCuisines: List<String>
)