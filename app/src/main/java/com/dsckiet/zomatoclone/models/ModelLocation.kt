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
    val name: String = ""
) : Parcelable