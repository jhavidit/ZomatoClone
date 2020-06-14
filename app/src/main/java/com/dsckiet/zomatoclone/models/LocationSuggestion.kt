package com.dsckiet.zomatoclone.models


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationSuggestion(
    @Json(name = "country_flag_url")
    val countryFlagUrl: String,
    @Json(name = "country_id")
    val countryId: Int,
    @Json(name = "country_name")
    val countryName: String,
    @Json(name = "discovery_enabled")
    val discoveryEnabled: Int,
    @Json(name = "has_go_out_tab")
    val hasGoOutTab: Int,
    @Json(name = "has_new_ad_format")
    val hasNewAdFormat: Int,
    val id: Int,
    @Json(name = "is_state")
    val isState: Int,
    val name: String,
    @Json(name = "should_experiment_with")
    val shouldExperimentWith: Int,
    @Json(name = "state_code")
    val stateCode: String,
    @Json(name = "state_id")
    val stateId: Int,
    @Json(name = "state_name")
    val stateName: String
):Parcelable