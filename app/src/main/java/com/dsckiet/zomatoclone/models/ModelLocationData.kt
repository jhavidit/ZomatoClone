package com.dsckiet.zomatoclone.models


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ModelLocationData(
    @Json(name = "has_more")
    val hasMore: Int,
    @Json(name = "has_total")
    val hasTotal: Int,
    @Json(name = "location_suggestions")
    val locationSuggestions: List<LocationSuggestion>,
    val status: String,
    @Json(name = "user_has_addresses")
    val userHasAddresses: Boolean
):Parcelable