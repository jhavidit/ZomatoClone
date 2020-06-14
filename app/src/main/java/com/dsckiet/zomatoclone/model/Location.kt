package com.dsckiet.zomatoclone.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val address: String,
    val city: String,
    @Json(name = "city_id")
    val cityId: Int,
    @Json(name = "country_id")
    val countryId: Int,
    val latitude: String,
    val locality: String,
    @Json(name = "locality_verbose")
    val localityVerbose: String,
    val longitude: String,
    val zipcode: String
) : Parcelable