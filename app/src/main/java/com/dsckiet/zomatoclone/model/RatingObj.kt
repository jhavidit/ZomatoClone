package com.dsckiet.zomatoclone.model


import com.squareup.moshi.Json

data class RatingObj(
    @Json(name = "bg_color")
    val bgColor: BgColor,
    val title: Title
)