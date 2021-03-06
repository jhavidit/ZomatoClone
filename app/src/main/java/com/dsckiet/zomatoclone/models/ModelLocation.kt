package com.dsckiet.zomatoclone.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ModelLocation(
    val best_rated_restaurant: List<restaurant>,
    val city: String
) : Parcelable

@Parcelize
data class restaurant(
    val restaurant: restaurantDetails
) : Parcelable

@Parcelize
data class restaurantDetails(
    val name: String = "",
    val cuisines: String,
    val timings: String,
    @Json(name = "average_cost_for_two")
    val averageCostForTwo: Int,
    val currency: String,
    @Json(name = "phone_numbers")
    val phoneNumbers: String,
    @Json(name = "photos_url")
    val photosUrl: String,
    @Json(name = "menu_url")
    val menuUrl: String,
    @Json(name = "url")
    val orderUrl: String,
    val location: LocationXX,
    val thumb: String,
    @Json(name = "user_rating")
    val userRating: UserRating
) : Parcelable

@Parcelize
data class LocationXX(
    val address: String
) : Parcelable



@Parcelize
data class UserRating(
    @Json(name = "aggregate_rating")
    val aggregateRating: String,
    @Json(name = "rating_color")
    val ratingColor: String,
    @Json(name = "rating_text")
    val ratingText: String,
    val votes: String
) : Parcelable