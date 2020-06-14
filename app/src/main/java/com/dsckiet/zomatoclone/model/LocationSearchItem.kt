package com.dsckiet.zomatoclone.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationSearchItem(
    @Json(name = "country_id")
    val countryId: String,
    @Json(name = "country_name")
    val countryName: String,
    val id: String,
    @Json(name = "is_state")
    val isState: String,
    val name: String,
    @Json(name = "state_code")
    val stateCode: String,
    @Json(name = "state_id")
    val stateId: String,
    @Json(name = "state_name")
    val stateName: String
): Parcelable