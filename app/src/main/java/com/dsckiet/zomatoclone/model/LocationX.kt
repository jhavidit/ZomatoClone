package com.dsckiet.zomatoclone.model


import com.squareup.moshi.Json

data class LocationX(
    @Json(name = "city_id")
    val cityId: Int,
    @Json(name = "city_name")
    val cityName: String,
    @Json(name = "country_id")
    val countryId: Int,
    @Json(name = "country_name")
    val countryName: String,
    @Json(name = "entity_id")
    val entityId: Int,
    @Json(name = "entity_type")
    val entityType: String,
    val latitude: Double,
    val longitude: Double,
    val title: String
)