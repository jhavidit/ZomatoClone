package com.dsckiet.zomatoclone.models


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationSearch(
    @Json(name = "has_more")
    val hasMore: Int,
    @Json(name = "has_total")
    val hasTotal: Int,
    @Json(name = "location_suggestions")
    val locationSuggestions: List<LocationSuggestionX>,
    val status: String,
    @Json(name = "user_has_addresses")
    val userHasAddresses: Boolean
):Parcelable